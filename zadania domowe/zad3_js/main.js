// Teoria Współbieżnośi, implementacja problemu 5 filozofów w node.js
// Opis problemu: http://en.wikipedia.org/wiki/Dining_philosophers_problem
//   https://pl.wikipedia.org/wiki/Problem_ucztuj%C4%85cych_filozof%C3%B3w
// 1. Dokończ implementację funkcji podnoszenia widelca (Fork.acquire).
// 2. Zaimplementuj "naiwny" algorytm (każdy filozof podnosi najpierw lewy, potem
//    prawy widelec, itd.).
// 3. Zaimplementuj rozwiązanie asymetryczne: filozofowie z nieparzystym numerem
//    najpierw podnoszą widelec lewy, z parzystym -- prawy.
// 4. Zaimplementuj rozwiązanie z kelnerem (według polskiej wersji strony)
// 5. Zaimplementuj rozwiążanie z jednoczesnym podnoszeniem widelców:
//    filozof albo podnosi jednocześnie oba widelce, albo żadnego.
// 6. Uruchom eksperymenty dla różnej liczby filozofów i dla każdego wariantu
//    implementacji zmierz średni czas oczekiwania każdego filozofa na dostęp
//    do widelców. Wyniki przedstaw na wykresach.


function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
}

var Fork = function () {
    this.state = 0;
    return this;
}

function releaseBothForks(forks, f1, f2) {
    forks[f1].release();
    forks[f2].release();
}

/** PODNIES WIDELCE - where the JS magic begins (prototype :)
 */

// Funkcja podnosi jeden semafor po czym coś wykonuje
Fork.prototype.acquire = function (cb) {
    var sleepTime = 1;
    var fork = this;
    loop = function (time) {
        function pickUpSingle() {
            if (fork.state === 0) {
                fork.state = 1;
                cb();
            } else {
                loop(time * 2);
            }
        }

        setTimeout(function () {
            pickUpSingle();
        }, getRandomInt(1, time));

    };
    loop(sleepTime);
}


Fork.prototype.release = function () {
    this.state = 0;
}

function resetForks(forks) {
    for (var i = 0; i < forks.length; i++) {
        forks[i].release();
    }
}

var Philosopher = function (id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id + 1) % forks.length;
    this.startTime;
    this.endTime;
    this.times = [];
    this.hasEnded = false;
    return this;
}

Philosopher.prototype.startNaive = function (count) {
    var forks = this.forks;
    var f1 = this.f1;
    var f2 = this.f2;
    var id = this.id;

    var fun = function (count) {
        if (count > 0) {
            forks[f1].acquire(
                function () {
                    forks[f2].acquire(
                        function () {
                            setTimeout(
                                function () {
                                    //console.log("Pojadł filozof" + id)
                                    releaseBothForks(forks, f1, f2);
                                    fun(count - 1);
                                }, getRandomInt(1, 5))
                        }
                    )
                });
        }
    }
    /// bo zakleszczal sie po 1 obiegu -> tutaj po chwili, bardzo krotkiej ale jednak
    setTimeout(
        function () {
            fun(count)
        }, getRandomInt(1, 10));
    // zaimplementuj rozwiązanie naiwne
    // każdy filozof powinien 'count' razy wykonywać cykl
    // podnoszenia widelców -- jedzenia -- zwalniania widelców
}


Philosopher.prototype.startAsym = function (count) {
    var forks = this.forks;
    var f1, f2;
    var id = this.id;

    function makeForkOrder() {
        if (id % 2 === 0) {
            f1 = this.f2;
            f2 = this.f1;
        } else {
            f1 = this.f1;
            f2 = this.f2;
        }
    }

    // flow podejscia
    makeForkOrder.call(this);
    var fun = function (count) {
        if (count > 0) {
            forks[f1].acquire(
                function () {
                    forks[f2].acquire(
                        function () {
                            setTimeout(function () {
                                console.log("Pojadł filozof" + id + "  count: " + count);
                                releaseBothForks(forks, f1, f2);
                                fun(count - 1);
                            }, getRandomInt(1, 5))
                        }
                    )
                });
        }
    }
    fun(count);

    // zaimplementuj rozwiązanie asymetryczne
    // każdy filozof powinien 'count' razy wykonywać cykl
    // podnoszenia widelców -- jedzenia -- zwalniania widelców
}

Conductor = function (amount) {
    this.tableTaken = [];
    this.N = amount;
    for (let i = 0; i < N; i++) {
        this.tableTaken.push(0);
    }
    return this;
}

Conductor.prototype.release = function (i) {
    this.tableTaken[i] = 0;
}

Conductor.prototype.tryAquire = function () {
    var sitting = 0;
    for (let i = 0; i < this.N; i++) {
        sitting += philosophers[i];
    }
    if (sitting === N - 1) {
        return false;
    }
    return true;

}

Philosopher.prototype.startConductor = function (conductor, count) {
    var forks = this.forks;
    var f1 = this.f1;
    var f2 = this.f2;
    var id = this.id;

    // tutaj też trzeba niestety ustalać kolejność widelców - Wikipedia
    function makeForkOrder() {
        if (id % 2 === 0) {
            f1 = this.f2;
            f2 = this.f1;
        } else {
            f1 = this.f1;
            f2 = this.f2;
        }
    }

    // flow podejscia
    makeForkOrder.call(this);
    var fun = function (count) {
        if (count > 0) {
            if (conductor.tryAquire) {
                forks[f1].acquire(
                    function () {
                        forks[f2].acquire(
                            function () {
                                setTimeout(function () {
                                    console.log("Pojadł filozof" + id + "  count: " + count);
                                    releaseBothForks(forks, f1, f2);
                                    conductor.release(this.id);
                                    fun(count - 1);
                                }, getRandomInt(1, 5))
                            }
                        )
                    });
            }
        }
    }
    fun(10);


    // zaimplementuj rozwiązanie z kelnerem
    // każdy filozof powinien 'count' razy wykonywać cykl
    // podnoszenia widelców -- jedzenia -- zwalniania widelców
}

function acquireTwo(f1, f2, cb) {
    var sleepTime = 1;

    var loop = function (waitTime) {
        function pickUpTwo() {
            if (f1.state === 0 && f2.state === 0) {
                f1.state = 1;
                f2.state = 1;
                cb();
            } else {
                loop(waitTime * 2);
            }
        }

        setTimeout(function () {
            pickUpTwo();
        }, getRandomInt(1, waitTime));

    };
    loop(sleepTime);
}

Philosopher.prototype.acquireTwoForksAtOnce = function (count) {
    var forks = this.forks;
    var f1 = this.f1;
    var f2 = this.f2;
    var id = this.id;
    var philo = this;

    var fun = function (count) {
        if (count > 0) {
            philo.startTime = new Date().getTime();
            acquireTwo(forks[f1], forks[f2],
                function () {
                    setTimeout(function () {
                        console.log("Pojadł filozof " + id + "  count: " + count);
                        releaseBothForks(forks, f1, f2);
                        philo.endTime = new Date().getTime();
                        philo.times.push(philo.endTime - philo.startTime);
                        fun(count - 1);
                    }, getRandomInt(1, 5))
                }
            );
        }else{
            philo.hasEnded = true;
        }
    }
    fun(count);
}


// TODO: wersja z jednoczesnym podnoszeniem widelców
// Algorytm BEB powinien obejmować podnoszenie obu widelców,
// a nie każdego z osobna


var N = 5;
var forks = [];
var philosophers = []
var conductor = new Conductor(N);

console.log("Program starts")
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    ///philosophers[i].startNaive(10);
    ///philosophers[i].startAsym(10);
    ///philosophers[i].startConductor(conductor, 10);
    philosophers[i].acquireTwoForksAtOnce(10)
}

var allEnded = false;
setTimeout(function () {
while(!allEnded){
    allEnded = true;
    for (let i = 0; i < N; i++) {
        if(philosophers[i].hasEnded === false){
            allEnded = false;
        }
    }
}
for (let i = 0; i < N; i++) {
    for (let j = 0; j < philosophers[i].times.length; j++) {
            console.log("ID: " + i + " time: " + philosophers[i].times[j])
    }
}}, 1000);




/// "var fun" zrobienie var a bez vara zupełnie
/// zmienia działanie programu -> tworzy obiekt
/// https://stackoverflow.com/questions/336859/var-functionname-function-vs-function-functionname

//// https://stackoverflow.com/questions/555191/javascript-semaphore-test-and-set-lock
