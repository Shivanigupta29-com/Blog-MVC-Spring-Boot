var page = 0;
const getMyPost = () => {
    $.ajax({
        url: `/api/v1/post/my?page=${page}`,
        headers: {"token": localStorage.getItem("token")},
        type: "GET",
        dataType: "json",
        contentType: 'application/json',
        success: function(result) {
            result.forEach(element => {
                $('#Blogs').append(
                    `
                        <div class="card mt-3">
                          <div class="card-header">
                            ${element.title}
                          </div>
                          <div class="card-body">
                            <blockquote class="blockquote mb-0">
                              <p>${element.body}</p>
                              <footer class="blockquote-footer">By <cite title="Source Title">${element.author}</cite></footer>
                            </blockquote>
                          </div>
                        </div>
                    `
                )
            });
            if(result.length == 0){
                $('#loadMore').addClass('d-none')
                $('#noMoreItem').removeClass('d-none')
            }
            page+=1;
        }
    });
}

const createPost = () => {
    var data = {
            'title': $('#title').val(),
            'body': $('#body').val(),
            'userId': localStorage.getItem("id")
        }
    jQuery.ajax({
              url: "/api/v1/post/create",
              type: "POST",
              data: JSON.stringify(data),
              dataType: "json",
              contentType: 'application/json',
              success: function(result) {
                if(!alert('Post created successfully!')){
                   window.location.reload();
                }
              },
              error: function(e) {
                console.log(e);
                alert('Post creation failed')
              },
    });
}

getMyPost()