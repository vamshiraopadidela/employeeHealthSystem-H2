INSERT INTO employee.email_template(
	email_template_id, email_template, template,email_subject)
	VALUES ('1', 'EMPLOYEE_INSURANC_REQUEST', '
Respected [employeeName],

This is to bring in your kind attention that our company has decided to introduce Health Insurance Policy for the employees. As you all are well 
familiar with the fact that our country is becoming a very expensive country to live in and medical treatment is quite exorbitant. We have advised 
our policy makers for the health insurance policy. It is a factual point that only company affords to give its employees health insurance Company, 
when employee contributions towards work target and sales have been remarkable. This time we have decided to share the contributions with employees. 
This announcement is for all the employees of the office. This health insurance policy would cover most of your monthly medical treatments. 
It includes major and minor treatments. It also includes your spouse and children health treatments insurance.

Please provide Required Details
1) Dependent Name
2) Relation to Employee
3) Date of Birth
4) Employee Id 

Warm Regards,

HR Head','Requesting the Dependent Details for Health Insurance Policy');

INSERT INTO employee.email_template(
	email_template_id, email_template, template,email_subject)
	VALUES ('2', 'BROKER_INSURANC_REQUEST', '
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>A responsive two column example</title>
    <style>
        /* A simple css reset */
        @media (min-width:20px){
table[class="container"] {width: 100%;} /* we want to use the full device width for mobiles */
table[class="table-left"] {width: 100%;}
table[class="table-right"] {width: 100%;} /* the right table should be fluid */
}

@media (min-width:480px)
{
table[class="table-left"] {width: 50%;} /* we want 50:50 tables if the device-width is >= 480px */
table[class="table-right"] {width: 50%;}
}

@media (min-width:600px)
{
table[class="container"] {width: 600px;} /* the container table should not extend 600px */
}
    </style>
</head>

<body>
 <p> Hi Team, </p>
 <p>This mail is regarding the insurance policy of new onboarded employees. Please find the details below. </p>
    <table id="bgtable" align="center" border="0" cellpadding="0" cellspacing="0" height="20%" width="100%">
    <tr>
        <td align="center" valign="top">
            <!-- container 600px -->
            <table border="1" cellpadding="0" cellspacing="0" class="container" width="600" style="...">
                <tr>
                    <td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Employee Id</td>
                            </tr>
                        </table>
					</td>
					<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[employeeId]</td>
                            </tr>
                        </table>
					</td>
				</tr>
				<tr>
                    <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Employee Name</td>
                            </tr>
                        </table>
					</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[employeeName]</td>
                            </tr>
                        </table>
				</td>
				</tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Dependent Name</td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[dependentName]</td>
                            </tr>
                        </table>
				</td>
                </tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Relation To Employee</td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[relationToEmployee]</td>
                            </tr>
                        </table>
				</td>
                </tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Date of Birth</td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[dob]</td>
                            </tr>
                        </table>
				</td>
                </tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Email </td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[email]</td>
                            </tr>
                        </table>
				</td>
                </tr>
            </table>
            <!-- container 600px -->
        </td>
    </tr>
</table>
<p>Warm Regards,</p>
<p>HR Head</p>
</body>
</html>
','Sending the Dependent Details for Health Insurance Policy');

INSERT INTO employee.email_template(
	email_template_id, email_template, template,email_subject)
	VALUES ('3', 'BROKER_DEACTIVATE_REQUEST', '
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>A responsive two column example</title>
    <style>
        /* A simple css reset */
        @media (min-width:20px){
table[class="container"] {width: 100%;} /* we want to use the full device width for mobiles */
table[class="table-left"] {width: 100%;}
table[class="table-right"] {width: 100%;} /* the right table should be fluid */
}

@media (min-width:480px)
{
table[class="table-left"] {width: 50%;} /* we want 50:50 tables if the device-width is >= 480px */
table[class="table-right"] {width: 50%;}
}

@media (min-width:600px)
{
table[class="container"] {width: 600px;} /* the container table should not extend 600px */
}
    </style>
</head>

<body>
 <p> Hi Team, </p>
 <p>This mail is regarding the deactivating insurance policy of employees. Please find the details below. </p>
    <table id="bgtable" align="center" border="0" cellpadding="0" cellspacing="0" height="20%" width="100%">
    <tr>
        <td align="center" valign="top">
            <!-- container 600px -->
            <table border="1" cellpadding="0" cellspacing="0" class="container" width="600" style="...">
                <tr>
                    <td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Employee Id</td>
                            </tr>
                        </table>
					</td>
					<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[employeeId]</td>
                            </tr>
                        </table>
					</td>
				</tr>
				 <tr>
                    <td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Card Id</td>
                            </tr>
                        </table>
					</td>
					<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[cardId]</td>
                            </tr>
                        </table>
					</td>
				</tr>
				<tr>
                    <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Employee Name</td>
                            </tr>
                        </table>
					</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[employeeName]</td>
                            </tr>
                        </table>
				</td>
				</tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Dependent Name</td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[dependentName]</td>
                            </tr>
                        </table>
				</td>
                </tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Relation To Employee</td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[relationToEmployee]</td>
                            </tr>
                        </table>
				</td>
                </tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Date of Birth</td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[dob]</td>
                            </tr>
                        </table>
				</td>
                </tr>
				<tr>
				 <td>
						<table align="left" border="0" cellpadding="0" cellspacing="0" class="table-left"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>Email </td>
                            </tr>
                        </table>
						
				</td>
				<td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" class="table-right"
                            style="display: inline-block;" width="300">
                            <tr>
                                <td>[email]</td>
                            </tr>
                        </table>
				</td>
                </tr>
            </table>
            <!-- container 600px -->
        </td>
    </tr>
</table>
<p>Warm Regards,</p>
<p>HR Head</p>
</body>
</html>
','Deactivating the Dependent Details for Health Insurance Policy');