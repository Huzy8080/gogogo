//函数
function greet(how, whom) {
    return how + "," + whom + "!";
}

function welcome(whom) {
    return "hello, " + whom;
}

//面向对象
function Greeter(how) {
    this.how = how;
    Greeter.prototype.welcome = function (whom) {
        return this.how + "," + whom + "!";
    }
}