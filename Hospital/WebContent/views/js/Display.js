//Hide Physician and Patient Details

		$(document).ready(function(){

			$('div#patientdetails').hide();
			$('div#physiciandetails').hide();

		});

// Show Physician Details on Submit		

		$('#physicianSubmit').click(function(){

			$('div#physiciandetails').show();
		});

// Show Patient Details on Submit		

		$('#patientSubmit').click(function(){

			$('div#patientdetails').show();
		});