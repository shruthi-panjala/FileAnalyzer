<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="stylesheet" href="bootstrap.css">
<meta name="viewport" content="width=device-width, intial-scale">

<script src="bootstrap.min.js"></script>
<style>
h1,h3{
color:black;
}
        table,td{
          border-collapse:collapse;   
        color: black;
             height:50px;
text-align: left;   
       border: none;
       }
        body {
            background:transparent;
            font-family: sans-serif;
        background: teal;
            
 min-height: 2000px;
  padding-top: 70px; 
        }
       </style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>FileAnalyzer Application</title>
</head>

<body>
<h1 align=center>File Analyzer</h1>

<form name="myform" action="new" method="post">
<div class="container">
<div class="panel panel-warning" id="file2">
       
        <div class="panel-heading">
         <div class="panel-title">
        <h3 align=center>Get more information about files in your System</h3></div></div>
       <div class="panel-body">
  
           <table align="center" class="table table-borderless">
<tr>
<td><label>Choose File</label></td>
<td><input type="file" name="uploadfile" id="uploadfile" class="form-control" required="required" />
</td></tr>
<tr>
<td></td>
<td> <button type="submit" class="btn btn-success">submit</button> <button type="reset" class="btn btn-warning">cancel</button></td></tr>
</table>
</div>
</div></div>
</form>
</body>
</html>