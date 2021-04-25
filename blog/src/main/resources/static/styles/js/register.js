$("#registerForm").submit(function(e){
    e.preventDefault();
    var data = {
            'email': $('#email').val(),
            'username': $('#username').val(),
            'password': $('#password').val()
    }
    jQuery.ajax({
              url: "/api/v1/user/register",
              type: "POST",
              data: JSON.stringify(data),
              dataType: "json",
              contentType: 'application/json',
              success: function(result) {
                localStorage.setItem("id", result.id);
                localStorage.setItem("username", result.username);
                localStorage.setItem("token", result.token);
                if(!alert('user Successfully Registered!')){
                   document.location.href="/";
                }
              },
              error: function(e) {
                console.log(e);
                alert('User Already registered')
              },
    });
});