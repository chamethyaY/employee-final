//here you can see js is bit different because we use  a jQuery library.
//This is the js part that call the REST API.
$(document).ready(function() {
    getAllEmployees(); // Load employees when page is ready
});
//when user click the save button we have to catch the name,address and the number.we do not need to catch the id because it is auto generated.
function saveEmployee() {

    let name = $('#exampleFormControlInput2').val();
    let address = $('#exampleFormControlInput3').val();
    let number = $('#exampleFormControlInput4').val();

   //here we use a jQuery library it gives the ajax function that  we can send the frontend objects to the backend.
    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/saveEmployee",
        async:true,
        data: JSON.stringify({

            "empName": name,
            "empAddress": address,
            "empNumber": number
        }),
        //here we are checking whether the json object pass to the backend correctly or not.
        success: function(response) {
            alert("Saved successfully!");
            getAllEmployees()
        },
        error: function(xhr, status, error) {
            alert("Error: " + xhr.responseText);
        }
    });
}

//actually update is done first we clicking the row in the table that we want to update.then we do the update and save.
function updateEmployee() {
    let empID=$('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let address = $('#exampleFormControlInput3').val();
    let number = $('#exampleFormControlInput4').val();



    $.ajax({
        method: "PUT",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/updateEmployee",
        async:true,
        data: JSON.stringify({
            "empID":empID,
            "empName": name,
            "empAddress": address,
            "empNumber": number
        }),
        success: function(response) {
            alert("Saved successfully!");
            //we are calling the getAllEmployee because when we user click the button update it should be update in the table.
            getAllEmployees()
        },
        error: function(xhr, status, error) {
            alert("Error: " + xhr.responseText);
        }
    });
}


//we do not need to catch any element to delete.
//we have to catch the elements only we have to do a  change or save in the database.

function deleteEmployee() {
    let empID=$('#exampleFormControlInput1').val();

    $.ajax({
        method: "DELETE",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/deleteEmployee/"+empID,
        async:true,
        data: JSON.stringify({
            "empID":empID,

        }),
        success: function(response) {
            alert("Saved successfully!");
            getAllEmployees()
        },
        error: function(xhr, status, error) {
            alert("Error: " + xhr.responseText);
        }
    });
}


//It works without a button because you are calling the function directly in your JavaScript, instead of waiting for a user action.
//here we are getting the information so we do not have to catch any elements (no json data)
function getAllEmployees() {
    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/getAllEmployee",
        async: true,
        //response.content will hold all employee records that are currently saved in your database, not just the new ones.
        //here we doing taking all the data(employee object) to the table that visual to the user.
        success: function(response) {
             // Debug: see what is returned

            if (response.code === "00") {
                //tbody will hold all the rows of the table.
                let tbody = $('#empTable');
                //Ensures the table doesn’t duplicate rows every time you call the function.
                tbody.empty(); // clear old rows

                for (let emp of response.content) {
                    // make sure variable names match exactly
                    //creating the table row according to the j Query library.
                   // <tr>
                       // <td>1</td>
                       // <td>John Doe</td>
                       // <td>123 Street</td>
                       // <td>555-1234</td>
                    //</tr>

                    let row = `<tr>
                       <td>${emp.empID}</td>
                       <td>${emp.empName}</td>
                       <td>${emp.empAddress}</td>
                       <td>${emp.empNumber}</td>
                    </tr>`;
                    tbody.append(row);
                }
            } else {
                //this error happen when there is  a empty data.
                //Typo in JSON property name: e.g., Code instead of code.
                //Different API design: Maybe the server doesn’t always return code "00" for success.
                console.error("Unexpected code:", response.code);
            }
        },
        //If your frontend runs on localhost:63342 and backend on localhost:8080, the browser might block the request.
        //Backend not running
        //Wrong URL
        //Network issues
        //Response format mismatch
        error: function(xhr, status, error) {
            console.error("AJAX error:", xhr.responseText);
        }
    });
}

//when user click the table row the information in  the table row appear in the input field.
$(document).on('click', '#empTable tr', function () {
    var col0 = $(this).find('td:eq(0)').text();
    var col1 = $(this).find('td:eq(1)').text();
    var col2 = $(this).find('td:eq(2)').text();
    var col3 = $(this).find('td:eq(3)').text();

    $('#exampleFormControlInput1').val(col0);
    $('#exampleFormControlInput2').val(col1);
    $('#exampleFormControlInput3').val(col2);
    $('#exampleFormControlInput4').val(col3);
});






