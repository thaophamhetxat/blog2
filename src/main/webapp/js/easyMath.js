let sound1 = new Audio("./audio/canhcao.mp3");
let sound2 = new Audio("./audio/votay.mpeg");
let sound3 = new Audio("./audio/correct.mp3");
let sound4 = new Audio("./audio/nhacnen02.mp3");
let Tostart = confirm('Luật chơi: Bạn có 15 giây để chọn đáp án đúng để trả lời các câu hỏi ! ' +
    ' Trả lời đúng thì tăng thêm 2 giây ');
if (Tostart) {
    sound4.play();
    alert('START!');
} else {
    alert('Bye!');
    location.reload();
}

class GameTF {
    constructor() {
        this.score = 0;
        this.level = 1;
        this.result = '';
        this.operator = ["+", "-", "*"];
    }

    RandomNumber(min, max) {
        return Math.floor(Math.random() * max - min) + min;
    }

    RandomOperator() {
        let ran = Math.floor(Math.random() * this.operator.length);
        return this.operator[ran];
    }

    mathdisplay() {
        let number1 = this.RandomNumber(this.level, 4 * this.level);
        let number2 = this.RandomNumber(this.level, 4 * this.level);
        let op = this.RandomOperator();
        let dis = number1 + ' ' + op + ' ' + number2;
        if (this.level >= 5 && this.level < 15) {
            this.score = this.score + 2;
            sound4.play();
            dis = number1 + ' ' + op + ' ' + number2 + op + ' ' + this.RandomNumber(1, 3);
        }
        if (this.level >= 15) {
            this.score = this.score + 5;
            sound4.play();
            dis = number1 + ' ' + op + ' ' + number2 + '  ' + this.RandomOperator() + ' ' + this.RandomNumber(2, 6);
        }
        document.getElementById('display').innerHTML = dis;
        document.getElementById('result').innerHTML = this.RandomResult();
    }

    RandomResult() {
        return this.FakeResult();
    }

    getResult() {
        let dis = document.getElementById('display').innerHTML;
        this.result = eval(dis);
    }

    FakeResult() {
        this.getResult();
        let fakeresult = this.RandomNumber(this.result - 3, this.result + 3);
        return (fakeresult >= this.result) ? this.result : fakeresult
    }

    check(isChon) {
        let CheckResult = document.getElementById('result').innerHTML;
        let check;
        if (parseInt(CheckResult) !== this.result) {
            check = false;
        } else {
            check = true;
        }
        if (isChon !== check) {
            sound1.play();
            alert('game over')
            sound2.play();
            alert('Điểm của bạn là: ' + this.score + ' Bấm OK để chơi lại:');

            location.reload();
        } else {
            this.score++;
            this.level++;
            document.getElementById('score').innerHTML = 'Score: ' + this.score;
            document.getElementById('level').innerHTML = 'Level: ' + this.level;
            sound3.play();
            this.mathdisplay();
            timerun = timerun + 2;
        }
    }




}