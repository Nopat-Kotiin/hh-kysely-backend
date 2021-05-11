function addField() {
    let choice = document.getElementById('choices').value.split(" ")[0];

    // Create an empty input field element
    let rowIndex = document.getElementById('questions').getElementsByClassName('question-div').length;
    let input = document.createElement('input');
    input.id = "questions" + rowIndex + ".question";
    input.setAttribute("name", "questions[" + rowIndex + "].question");
    input.setAttribute("type", "text");
	input.style.width= '325px';
	
    // Create hidden field for type
    let typeInput = document.createElement('input');
    typeInput.value = choice;
    typeInput.type = "hidden";
    typeInput.id = "questions" + rowIndex + ".type";
    typeInput.setAttribute("name", "questions[" + rowIndex + "].type");

    // Create remove "-" button element
    let remove = document.createElement('button');
    remove.innerHTML = "-";
    remove.id = rowIndex;
    remove.setAttribute("onClick", "removeField(" + rowIndex + ")");
    remove.setAttribute("type", "button");
    remove.className = "btn btn-danger rm-btn";

    // Insert new table row to "questions", 2 new empty table data
    // and append input field and remove button to the newly created data
    let newDiv = document.createElement('div');
    document.getElementById("questions").appendChild(newDiv);
    newDiv.className = "question-div";

    let header = document.createElement('h5');
    switch (choice) {
        case 'text':
            header.innerText = 'Tekstikysymys';
            break;
        case 'radio':
            header.innerText = 'Monivalinta (valitse yksi)';
            break;
        case 'checkbox':
            header.innerText = 'Monivalinta (valitse monta)';
            break;
    }

    let wrapper = document.createElement('div');
    wrapper.appendChild(header);
    wrapper.appendChild(input);
    wrapper.appendChild(typeInput);
    wrapper.innerHTML += ' ';
    wrapper.appendChild(remove);
    newDiv.appendChild(wrapper);

    // If the question type is not text, add one choice
    if (choice !== 'text') {
        let choiceDiv = document.createElement('div');

        let choiceInput = document.createElement('input');
        choiceInput.id = "questions" + rowIndex + ".choices0";
        choiceInput.setAttribute("name", "questions[" + rowIndex + "].choices[0]");
        choiceInput.className = "choice";

        let removeChoice = document.createElement('button');
        removeChoice.innerHTML = "-";
        removeChoice.setAttribute("onClick", "removeChoice(" + rowIndex + ", " + 0 + ")");
        removeChoice.className = "btn btn-danger rm-choice-btn";
        removeChoice.setAttribute("type", "button");
        removeChoice.style.marginLeft = '50px';

        let addChoice = document.createElement('button');
        addChoice.innerHTML = "+";
        addChoice.setAttribute("onClick", "addChoice(" + rowIndex + ", " + 0 + ")");
        addChoice.className = "btn btn-success add-choice-btn";
        addChoice.setAttribute("type", "button");

        choiceDiv.appendChild(removeChoice);
        choiceDiv.innerHTML += ' ';
        choiceDiv.appendChild(choiceInput);
        choiceDiv.innerHTML += ' ';
        choiceDiv.appendChild(addChoice);
        newDiv.appendChild(choiceDiv);

    }
}

// Function for removing a field when the minus button is clicked
function removeField(id) {

    let parentDiv = document.getElementById('questions');
    let questions = document.getElementsByClassName('question-div');

    // Iterate through the table rows
    for (var i = 0, row; row = questions[i]; i++) {

        let current = parseInt(row.getElementsByTagName('button')[0].id);

        // subtract 1 from all elements' attributes after deletable question
        // so that it is parsed correctly on submission
        if (current > id) {
            const inputs = row.getElementsByTagName('input');
            for (var j = 0, input; input = inputs[j]; j++) {
                input.id = input.id.substr(0, 9) + (i - 1) + input.id.substr(10);
                input.name = input.name.substr(0, 10) + (i - 1) + input.name.substr(11);
            }

            const buttons = row.getElementsByClassName('rm-choice-btn');
            for (var j = 0, button; button = buttons[j]; j++) {
                button.setAttribute("onClick", "removeChoice(" + (i - 1) + ", " + j + ")")
            }

            const rmBtn = row.getElementsByClassName('rm-btn')[0];
            rmBtn.setAttribute("onClick", "removeField(" + (i - 1) + ")");
            const addBtn = row.getElementsByClassName('add-choice-btn')[0];
            if (addBtn) addBtn.setAttribute("onClick", "addChoice(" + (i - 1) + ", " + j + ")");
        }
    }
    // delete the element that the function was called from
    parentDiv.removeChild(questions[id]);
}

function validateForm() {
    var msg = "";
    const surveyName = document.forms["surveyForm"]["sname"].value;
    if (surveyName === "") {
        msg += "-Survey must have a name";
    }

    var emptyRows = 0;
    const questions = document.getElementById('questions').getElementsByTagName('input');
    if (questions.length === 0) msg += "\n- Survey must have at least 1 question";
    for (var i = 0, row; row = questions[i]; i++) {
        if (row.value === "" && row.className !== 'choice') {
            emptyRows++;
        }
    }

    var emptyChoices = 0;
    const choices = document.getElementsByClassName('choice');
    for (var i = 0, row; row = choices[i]; i++) {
        if (row.value === "") {
            emptyChoices++;
        }
    }

    if (emptyRows > 0) {
        msg += "\n-You have " + emptyRows + " empty questions";
    }
    if (emptyChoices > 0) {
        msg += "\n-You have " + emptyChoices + " empty choices";
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

function removeChoice(questionId, choiceId) {
    const question = document.getElementsByClassName('question-div')[questionId];
    const choices = question.getElementsByClassName('choice');
    if (choices.length === 1) {
        alert("You must have at least one choice in a question");
        return;
    }

    for (var i = 0, choice; choice = choices[i]; i++) {
        if (i >= choiceId) {
            choice.id = choice.id.substr(0, 18) + (i - 1);
            choice.name = choice.name.substr(0, 21) + (i - 1) + choice.name.substr(22);

            const buttons = choice.parentNode.getElementsByTagName('button');
            const rmBtn = buttons[0];
            rmBtn.setAttribute("onClick", "removeChoice(" + questionId + ", " + (i - 1) + ")")
            if (buttons.length > 1) {
                const addBtn = buttons[1];
                addBtn.setAttribute("onClick", "addChoice(" + questionId + ", " + (i - 1) + ")");
            }
        }
    }

    // move add button in case it would be deleted
    // could be improved by moving add button elsewhere
    if (choiceId === (choices.length - 1)) {
        const last = choices[choices.length - 1].parentNode;
        const addBtn = last.getElementsByClassName('add-choice-btn')[0];
        const secondLast = choices[choices.length - 2].parentNode;
        secondLast.innerHTML += ' ';
        secondLast.appendChild(addBtn);
    }

    question.removeChild(choices[choiceId].parentNode);
}

function addChoice(questionId, choiceId) {
    const question = document.getElementsByClassName('question-div')[questionId];
    const oldChoice = question.getElementsByClassName('choice')[choiceId].parentNode;
    const newChoiceDiv = document.createElement('div');

    let choiceInput = document.createElement('input');
    choiceInput.id = "questions" + questionId + ".choices" + (choiceId + 1);
    choiceInput.setAttribute("name", "questions[" + questionId + "].choices[" + (choiceId + 1) + "]");
    choiceInput.className = "choice";

    let removeChoice = document.createElement('button');
    removeChoice.innerHTML = "-";
    removeChoice.setAttribute("onClick", "removeChoice(" + questionId + ", " + (choiceId + 1) + ")");
    removeChoice.className = "btn btn-danger rm-choice-btn";
    removeChoice.setAttribute("type", "button");
    removeChoice.style.marginLeft = '50px';

    let addChoice = document.createElement('button');
    addChoice.innerHTML = "+";
    addChoice.setAttribute("onClick", "addChoice(" + questionId + ", " + (choiceId + 1) + ")");
    addChoice.className = "btn btn-success add-choice-btn";
    addChoice.setAttribute("type", "button");

    newChoiceDiv.appendChild(removeChoice);
    newChoiceDiv.innerHTML += ' ';
    newChoiceDiv.appendChild(choiceInput);
    newChoiceDiv.innerHTML += ' ';
    newChoiceDiv.appendChild(addChoice);

    question.appendChild(newChoiceDiv);
    oldChoice.removeChild(oldChoice.getElementsByClassName('add-choice-btn')[0]);
}