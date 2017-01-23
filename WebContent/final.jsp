<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, intial-scale">
<link rel="stylesheet" href="bootstrap.css">
<script src="bootstrap.min.js"></script>
<title>FileAnalyzer Apllication</title>
<style>
h2,h1{

color:teal;
}
        table,td{
          border-collapse:collapse;   
        color: teal;
             height:50px;
text-align: left;   
       border: none;
       }
        body {
            background:transparent;
            font-family: sans-serif;
        background: black;
           font-size:250% 
 min-height: 2000px;
  padding-top: 70px; 
        }
       </style>
</head>
<body color:yellow;>  
<h1 align=center><label>File Analyzer</label></h1>
<form name="myform1" action="" method="post">
<div class="container">
<div class="panel panel-success" id="file2">
       
        <div class="panel-heading">
         <div class="panel-title">
        <h2>Properties</h2></div></div>
       <div class="panel-body">
  
           <table align="center" class="table table-borderless">
<tr>
<td><label>File Name:</label></td><td>${myfile.fileName}</td>
</tr>
<tr>
<td><label>Date Created:</label></td><td>${myfile.dateCreated}</td></tr>
<tr>
<td><label>Last Modified Date:</label></td><td>${myfile.dateMod}</td></tr>
<tr>
<td><label>Last Access Date :</label></td><td>${myfile.lastAccess}</td>
</tr>
<tr>
<td><label>Size(in Bytes):</label></td><td>${myfile.size}</td></tr>
<tr>
<td><label>Total number of words:</label></td><td>${myfile.wordCount}</td></tr>
<tr><td><label>Total  number of lines:</label></td><td>${myfile.lineCount}</td></tr>
<tr><td><label>Total number of paragraphs:</label></td><td>${myfile.paragraphCount}</td></tr>
<tr><td><label>Words in Uppercase:</label></td><td>${myfile.capitalwords}</td></tr>
<tr><td><label>Punctuation violations:</label></td><td>${myfile.message}</td></tr>
</table>
</div>
<h2 align="center"><i>ThankYou...!!Want to upload more Files<a href="fileUpload.jsp">-Clickhere</a></i></h2>
</div>
</div>
</form>
</body>
</html>