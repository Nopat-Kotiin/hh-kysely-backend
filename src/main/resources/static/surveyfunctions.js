function addField() {
        	
        	// Create an empty input field element
            let rowIndex = document.getElementById('questions').getElementsByTagName('tr').length;
            let input = document.createElement('input');
            input.id = "questions" + rowIndex + ".question";
            input.setAttribute("name", "questions[" + rowIndex + "].question");
            input.setAttribute("type", "text");
            
			// Create remove "-" button element
            let remove = document.createElement('button');
            remove.innerHTML = "-";
            remove.id = rowIndex;
            remove.setAttribute("onClick", "removeField(" + rowIndex + ")");
            remove.setAttribute("type", "button");
            remove.className = "btn btn-danger";
            
			// Insert new table row to "questions", 2 new empty table data
			//and append input field and remove button to the newly created data
            let tr = document.getElementById("questions").insertRow(-1);
            let tdField = document.createElement('td');
            let tdRemove = document.createElement('td');
            tdField.appendChild(input);
            tr.appendChild(tdField);
            tdRemove.appendChild(remove);
            tr.appendChild(tdRemove);
        }
        
        // Function for removing a field when the minus button is clicked
        function removeField(id) {
        	
            let table = document.getElementById('questions');
            
            // Iterate through the table rows
            for (var i = 0, row; row = table.rows[i]; i++) {
                
                //delete the row that called the function
                let current = parseInt(row.getElementsByTagName('button')[0].id);
                if (current === id &&  id != 0) {
                    table.deleteRow(i);
                }
                
                //arrange the table data back in order
                if (current >= id &&  id != 0) {
                    let input1 = table.rows[i].getElementsByTagName("input")[0];
                    let button = table.rows[i].getElementsByTagName("button")[0];
                    input1.id = input1.id.substr(0, 9) + i + input1.id.substr(10);
                    input1.name = input1.name.substr(0, 10) + i + input1.name.substr(11);
                    if (table.rows[i].getElementsByTagName("input").length > 1) {
                        let input2 = table.rows[i].getElementsByTagName("input")[1];
                        input2.id = input2.id.substr(0, 9) + i + input2.id.substr(10);
                        input2.name = input2.name.substr(0, 10) + i + input2.name.substr(11);
                    }
                    button.id = i;
                    button.setAttribute("onClick", "removeField(" + i + ")");
                }
            }
        }
        
        function validateForm() {
            var msg = "";
    		const surveyName = document.forms["surveyForm"]["sname"].value;
    		if(surveyName === "") {
    			msg += "Survey must have a name";
    		}

            var emptyRows = 0;
            const questions = document.getElementById('questions').getElementsByTagName('input');
            for (var i = 0, row; row = questions[i]; i++) {
                if (row.value === "") {
                    emptyRows++;
                }
            }

            if (emptyRows > 0) {
                msg += "\nand you have " + emptyRows + " empty questions";
            }
            if (msg.length > 0) {
                alert(msg);
                return false;
            }
    	}
    	
    	function ResetNew(){
    		
    		document.getElementById('questions').innerHTML="<table id='questions'>" +
    		" <tbody><tr>" +
    		" <td>" +
    		" <input type='text' id='questions0.question' name='questions[0].question' value=''>" +
    		"    <input type='hidden' id='questions0.id' name='questions[0].id' value='0'>" +
    		"  </td>" +
    		" <td>" +
    		" <button type='button' class='btn btn-danger' id='0' onclick='removeField(0)'>-</button>" +
    		"  </td>" +
    		"  </tr>" +
    		" </tbody></table>";

    		
    		
    		document.getElementById('sname').value = "";
    		document.getElementById('sdescription').value = "";
    		
    		
    	}