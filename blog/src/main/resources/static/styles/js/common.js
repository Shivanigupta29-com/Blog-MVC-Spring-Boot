$( document ).ready(function() {
       var token = localStorage.getItem("token");
       var id = localStorage.getItem("id");
       var username = localStorage.getItem("username");

       if(token != null || id != null && username !=null){
           $('#myPost').removeClass('d-none')
           $('#likedPost').removeClass('d-none')
           $('#usernameText').removeClass('d-none')
           $('#login').addClass('d-none')
           $('#register').addClass('d-none')
           $('#usernameText').text(`Welcome ${username}` )
       }
       else{
           $('#myPost').addClass('d-none')
           $('#likedPost').addClass('d-none')
           $('#usernameText').addClass('d-none')
           $('#login').removeClass('d-none')
           $('#register').removeClass('d-none')
       }
});