// CLIENT-MODEL================================================================
function validateCustomerForm()
{
// CODE
if ($("#customerCode").val().trim() == "")
{
return "Insert Customer Code.";
}
// NAME
if ($("#customerName").val().trim() == "")
{
return "Insert Customer Name.";
}
// PHONE-------------------------------
if ($("#customerPhone").val().trim() == "")
{
return "Insert Customer Phone.";
}
// DESCRIPTION------------------------
if ($("#customerDesc").val().trim() == "")
{
return "Insert customer Description.";
}
return true;
}


$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});





// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateCustomerForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
$("#formCustomer").submit();
});







// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());
$("#customerCode").val($(this).closest("tr").find('td:eq(0)').text());
$("#customerName").val($(this).closest("tr").find('td:eq(1)').text());
$("#customerPhone").val($(this).closest("tr").find('td:eq(2)').text());
$("#customerDesc").val($(this).closest("tr").find('td:eq(3)').text());
});
// CLIENT-MODEL================================================================
function validateCustomerForm()
{
// CODE
if ($("#customerCode").val().trim() == "")
{
return "Insert Customer Code.";
}
// NAME
if ($("#customerName").val().trim() == "")
{
return "Insert Customer Name.";
}

// PHONE-------------------------------
if ($("#customerPhone").val().trim() == "")
{
return "Insert Customer Phone.";
}
// DESCRIPTION------------------------
if ($("#customerDesc").val().trim() == "")
{
return "Insert Customer Description.";
}
return true;
}


$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateCustomerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "customerAPI", 
 type : type, 
 data : $("#formCustomer").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerSaveComplete(response.responseText, status); 
 } 
 }); 
});



function onCustomerSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 
 $("#hidCustomerIDSave").val(""); 
 $("#formCustomer")[0].reset(); 
}

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "customerAPI", 
 type : "DELETE", 
 data : "customerID=" + $(this).data("customerid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerDeleteComplete(response.responseText, status); 
 } 
 }); 
});



function onCustomerDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
 }

