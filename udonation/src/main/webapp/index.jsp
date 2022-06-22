<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>I love ISP551</h1>
	<img src="header.png" style="width: 93%; height: 90%; display: block; padding-top: 10px; margin-left: 38px; margin-right: 38px;">
	<div class='center'>
	<div class="header">Need assistance with Network Coverage? Ask us any questions.</div>
	<p>All field marked (<span>*</span>) are mandatory</p>
    <form action="AddReport" name="myForm" method="post" onsubmit="return validation()">
	<br>
    <label id="labelid" for="name">Full Name <span>*</span></label>
    <input type="text" class="forInput" name="custName" placeholder="Enter your full name" required>
    
    <br><br>
	<label id="labelid" for="idno">Identification Number <span>*</span></label>
	<input type="text" name="custID" class="forInput" id="idno" placeholder="Enter your identification number (without -)" required>
	
	<br><br>
	<label id="labelid" for="phoneno">Phone Number <span>*</span></label>
	<input type="text" name="custPhoneNumber" class="forInput" id="phoneno" placeholder="Enter your phone number (without -)" required>

    <br><br>
    <label id="labelid" for="email">Email <span>*</span></label>
    <input type="text" name="custEmail" class="forInput" id="email" placeholder="Enter your email" required>
	
	<br><br>
	<label id="labelid" for="address">Address <span>*</span></label>
	<input type="text" name="custAddress" class="forInput" id="address" placeholder="Enter your address" required>
	
	<br><br>
	<label id="labelid" for="city">City <span>*</span></label>
	<input type="text" name="custCity" class="forInput" id="city" placeholder="Enter your city" required>
	
	<br><br>
	<label id="labelid" for="postcode">Postcode <span>*</span></label>
	<input type="text" name="custPostcode" class="forInput" id="postcode" placeholder="Enter your postcode" required>
	
	<br><br>
	<label id="labelid" for="state">State <span>*</span></label>
     	<select name="custState" id="state" class="forInput">
         <option value="">--State--</option>
         <option value="Johor">Johor</option>
         <option value="Kedah">Kedah</option>
         <option value="Kelantan">Kelantan</option>
         <option value="Kuala Lumpur">Kuala Lumpur</option>
         <option value="Melaka">Melaka</option>
         <option value="Negeri Sembilan">Negeri Sembilan</option>
         <option value="Pahang">Pahang</option>
         <option value="Perak">Perak</option>
         <option value="Perlis">Perlis</option>
         <option value="Pulau Pinang">Pulau Pinang</option>
         <option value="Sabah">Sabah</option>
         <option value="Sarawak">Sarawak</option>
         <option value="Selangor">Selangor</option>
         <option value="Terengganu">Terengganu</option>
    	</select>
	
	<br>
	<label id="labelid" for="latitude">Latitude <span>*</span></label>
	<input type="text" name="latitude" class="forInput" id="latitude" placeholder="Enter latitude" required>
	
	<br><br>
	<label id="labelid" for="longitude">Longitude <span>*</span></label>
	<input type="text" name="longitude" class="forInput" id="longitude" placeholder="Enter longitude" required>
	
	<br><br>
	<label id="labelid" for="dates">Date of Issue <span>*</span></label>
	<input type="text" name="dateIssue" id="dates" placeholder="Enter Date (DD/MM/YYYY)" required/>
	
	<br><br>
	<label id="labelid" for="issuedetails">Issue Details <span>*</span></label>
    <textarea id="text" name="issueDetail" class="forInput" id="issuedetails" rows="5" cols="150" 
    placeholder="Please indicate your network coverage issue details"></textarea>
	
	<br><br>
	<button class="btn">SUBMIT</button>
   	</form>
    </div>


</body>
</html>