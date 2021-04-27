function addField() {

    let choice = document.getElementById('choices').value;

    // Create an empty input field element
    let rowIndex = document.getElementById('questions').getElementsByClassName('question-div').length;
    let input = document.createElement('input');
    input.id = "questions" + rowIndex + ".question";
    input.setAttribute("name", "questions[" + rowIndex + "].question");
    input.setAttribute("type", "text");

    // Create hidden field for type
    let typeInput = document.createElement('input');
    switch(choice) {
        case 'text':
            typeInput.value = "text";
            break;
        case 'radiobutton':
            typeInput.value = "radio";
            break;
        case 'checkbox':
            typeInput.value = "checkbox";
            break;
    }
    typeInput.type = "hidden";
    typeInput.id = "questions" + rowIndex + ".type";
    typeInput.setAttribute("name", "questions[" + rowIndex + "].type");

    // Create remove "-" button element
    let remove = document.createElement('button');
    remove.innerHTML = "-";
    remove.id = rowIndex;
    remove.setAttribute("onClick", "removeField(" + rowIndex + ")");
    remove.setAttribute("type", "button");
    remove.className = "btn btn-danger";

    // Insert new table row to "questions", 2 new empty table data
    //and append input field and remove button to the newly created data
    let newDiv = document.createElement('div');
    document.getElementById("questions").appendChild(newDiv);
    newDiv.className = "question-div";

    let wrapper = document.createElement('div');
    wrapper.appendChild(input);
    wrapper.appendChild(typeInput);
    wrapper.appendChild(remove);
    newDiv.appendChild(wrapper);

    // If the question type is not text, add one choice
    if(choice !== 'text') {
        let choiceDiv = document.createElement('div');

        let choiceInput = document.createElement('input');
        choiceInput.id = "questions" + rowIndex + ".choices0";
        choiceInput.setAttribute("name", "questions[" + rowIndex + "].choices[0]");
        choiceInput.className = "choice";

        let removeChoice = document.createElement('button');
        removeChoice.innerHTML = "-";
        removeChoice.setAttribute("onClick", "removeChoice(" + rowIndex + ", " + 0 + ")");
        removeChoice.className = "btn btn-danger";
        removeChoice.setAttribute("type", "button");
        removeChoice.style.marginLeft = '50px';

        let addChoice = document.createElement('button');
        addChoice.innerHTML = "+";
        addChoice.setAttribute("onClick", "addChoice(" + rowIndex + ", " + 0 + ")");
        addChoice.className = "btn btn-success";
        addChoice.setAttribute("type", "button");

        choiceDiv.appendChild(removeChoice);
        choiceDiv.appendChild(choiceInput);
        choiceDiv.appendChild(addChoice);
        newDiv.appendChild(choiceDiv);

    }
}

// TODO: this function is WIP and everything below is not done
// Function for removing a field when the minus button is clicked
function removeField(id) {

    let parentDiv = document.getElementById('questions');
    let questions = document.getElementsByClassName('question-div');

    if (questions.length === 1) {
        alert("You must have at least one question");
        return;
    }

    let toBeRemoved;
    // Iterate through the table rows
    for (var i = 0, row; row = questions[i]; i++) {

        let current = parseInt(row.getElementsByTagName('button')[0].id);
        console.log(current, id);

        //delete the row that called the function
        if (current === id) {
            toBeRemoved = current;
            //parentDiv.removeChild(row.parentNode);
        }

        if (current >= id) {
            console.log(row.getElementsByTagName('input'));
        }

        //arrange the table data back in order
        /* if (current >= id) {
            for (var j = 0, element; element = table.rows[i].getElementsByTagName("input")[j]; j++) {
                let input = table.rows[i].getElementsByTagName("input")[0];
                input.id = input.id.substr(0, 9) + i + input.id.substr(10);
                input.name = input.name.substr(0, 10) + i + input.name.substr(11);
            }
            let button = table.rows[i].getElementsByTagName("button")[0];
            button.id = i;
            button.setAttribute("onClick", "removeField(" + i + ")");
        } */
    }
    parentDiv.removeChild(questions[toBeRemoved].parentNode);
}

function validateForm() {
    var msg = "";
    const surveyName = document.forms["surveyForm"]["sname"].value;
    if (surveyName === "") {
        msg += "-Survey must have a name";
    }

    var emptyRows = 0;
    const questions = document.getElementById('questions').getElementsByTagName('input');
    for (var i = 0, row; row = questions[i]; i++) {
        if (row.value === "") {
            emptyRows++;
        }
    }

    if (emptyRows > 0) {
        msg += "\n-You have " + emptyRows + " empty questions";
    }
    if (msg.length > 0) {
        alert(msg);
        return false;
    }
}

function ResetNew() {

    document.getElementById('questions').innerHTML = "<table id='questions'>" +
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

function removeChoice(qId, cId) {
    console.log(qId, cId);
}