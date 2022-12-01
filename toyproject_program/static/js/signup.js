window.addEventListener("DOMContentLoaded", function () {
  // set();
});

function set() {
  setTimeout(() => {
    console.log(isRun);
    set();
  }, 1000);
}

let isRun = false;

function errorMessage(text) {
  if (isRun == false) {
    document.getElementById("signup_error").innerText = text;
    isRun = true;
    setTimeout(() => {
      document.getElementById("signup_error").innerText = "";
      isRun = false;
    }, 2000);
  }
}

function Signup() {
  let userEmail = $("#userEmail").val();
  let nick = $("#nick").val();
  let password = $("#password").val();
  let emailValCheck = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
  let pwdValCheck = /^[a-z\\d`~!@#$%^&*()-_=+]{8,24}$/;
  if (isRun == true) return;

  if (userEmail == "") {
    return errorMessage("이메일을 입력해주세요.");
  }
  if (userEmail.search(/\s/) != -1) {
    return errorMessage("이메일에 공백이 들어갈 수 없습니다.");
  }
  if (!emailValCheck.test(userEmail)) {
    return errorMessage("이메일 형식이 올바르지 않습니다.");
  }

  if (nick == "") {
    return errorMessage("닉네임을 입력해주세요.");
  }
  if (password == "") {
    return errorMessage("비밀번호를 입력해주세요.");
  }
  if (!pwdValCheck.test(password)) {
    return errorMessage(
      "비밀번호는 영소문자,숫자,특수문자로 구성하여 8글자~24자로 조합해서 사용해주세요."
    );
  }

  $.ajax({
    type: "POST",
    url: "/signup",
    data: { user_email: userEmail, user_pwd: password, user_nick: nick },
    success: function (res) {
      console.log(res);
      if (res.result == "FAIL") {
        let error_message = (document.getElementById(
          "signup_error"
        ).innerText = `이미 존재하는 이메일입니다.`);
        isRun = false;
        console.log(error_message);
      } else {
        alert("회원가입이 완료되었습니다.");
        window.location.href = "/";
      }
    },
  });
}
