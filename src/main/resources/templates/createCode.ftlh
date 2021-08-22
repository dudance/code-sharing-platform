<html>
    <head>
        <script>
            function send() {
                let object = {
                    "code": document.getElementById("code_snippet").value,
                    "time": document.getElementById("time_restriction").value,
                    "views": document.getElementById("views_restriction").value
                };

                let json = JSON.stringify(object);

                let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/code/new', false)
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);

                if (xhr.status == 200) {
                  alert("Success!");
                }
            }
        </script>
        <title>Create</title>
    </head>
    <body>
        <label>Time restriction:</label>
        <input id="time_restriction" type="text" value="0"/> </br>
        <label>Views restriction:</label>
        <input id="views_restriction" type="text" value="0"/> </br>
        <textarea id="code_snippet" rows="4" cols="50">// write your code here</textarea> </br>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </body>
</html>