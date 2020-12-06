<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>

<head>
    <title>
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
<nav class="navbar navbar-light navbar-center justify-content-center" style="background-color: #af250e;">
<span class="navbar-text" style="color: white;font-size: 50px;font-weight: bold;">
      Calculator
    </span>
</nav>
<div class="row text-center my-3 large">
    <div class="col-lg-3"></div>
    <div class="col-lg-9">
        <form class="form-inline">
            <div class="form-group mb-2">
                <label for="expression" class="sr-only">Enter Expression</label>
                <input type="text" readonly="" class="form-control-plaintext" id="expression" value="Enter Expression">
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="expressionBox" class="sr-only">Expression</label>
                <input type="text" class="form-control" id="expressionBox" placeholder="Expression">
            </div>
            <button type="button" id="submit" class="btn btn-primary mb-2" style="background-color: #af250e;
    border-color: #af250e;">Submit
            </button>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-9" id="result">
        <span id="errorResult" style="color: red"></span>
    </div>
</div>
<script type="text/javascript">
    $('#submit').click(function (e) {
        var expression = $('#expressionBox').val().replace(/\+/g, "%2b");
        $.ajax({
            type: 'GET',
            async: true,
            data: 'expression=' + expression,
            url: "/calculate",
            success: function (data) {
                var html = "<h4><b>Results</b></h4>";
                $.each(data.recordsList, function (key, value) {
                    html += "<span><b>Expression:</b>" + value.expression + ", </span>";
                    html += "<span><b>Result:</b>" + value.result + "</span>";
                    html += "<ul>";
                    html += "<li><span><b>Natural Number:</b>" + value.naturalNumber + "</span></li>";
                    html += "<li><span><b>Whole Number:</b>" + value.wholeNumber + "</span></li>";
                    html += "<li><span><b>Positive Number:</b>" + value.positiveNumber + "</span></li>";
                    html += "<li><span><b>Negative Number:</b>" + value.negativeNumber + "</span></li>";
                    html += "<li><span><b>Prime Number:</b>" + value.primeNumber + "</span></li><br/>";
                    html += "</ul>";
                });
                $('#result').html(html);
            },
            error: function (data) {
                $('#errorResult').html(data.responseJSON.msg[0]);

            }
        });
    })
</script>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</html>


