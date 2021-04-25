var page = 0;
const getPosts = () => {
    $.ajax({
        url: `/api/v1/post/top?page=${page}`,
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

getPosts()