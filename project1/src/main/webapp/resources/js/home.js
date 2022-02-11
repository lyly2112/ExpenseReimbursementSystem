/**
 *
 */
window.onload = function () {
  ajaxGetCurrrentUser();
};


(function (document) {
  "use strict";

  var TableFilter = (function (myArray) {
    var search_input;

    function _onInputSearch(e) {
      search_input = e.target;
      var statusCells = document.getElementsByClassName('statusId');
      var row = document.getElementById('ticketsTableBody').getElementsByTagName('tr');
      var search_val = search_input.value;
      var text_content = row.textContent;
      var rowCount = 0;

      var tables = document.getElementsByClassName(
        search_input.getAttribute("data-table")
      );

      myArray.forEach.call(tables, function (table) {
        myArray.forEach.call(table.tBodies, function (tbody) {
          myArray.forEach.call(tbody.rows, function (row) {
            console.log("row ===> ", row);
            console.log(statusCells[rowCount]);
            console.log(statusCells[rowCount].innerText);
            var text_content = statusCells[rowCount].innerText;
            if (statusCells[rowCount].innerText == search_val) {
              console.log("this is one!");
              row.style.display = "";
            }
            else if (search_val == "") {
              row.style.display = "";
            }
            else {
              row.style.display = "none";
            }
            rowCount++;
          });
        });
      });
    }

    return {
      init: function () {
        var inputs = document.getElementsByClassName("search-input");
        myArray.forEach.call(inputs, function (input) {
          input.oninput = _onInputSearch;
        });
      },
    };
  })(Array.prototype);

  document.addEventListener("readystatechange", function () {
    if (document.readyState === "complete") {
      TableFilter.init();
    }
  });
})(document);

function ajaxGetCurrrentUser() {
  let xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      console.log("readyState has become 4 AND the status code is 200");

      let currentUser = JSON.parse(xhttp.responseText);
      console.log("session ===>", currentUser);

      ourDOMManipulationCurrentUser(currentUser);

      if (currentUser.userRoleId == 1) {
        console.log("this is employee");
        showEmployeeScreen(currentUser);
      } else if (currentUser.userRoleId == 2) {
        console.log("this is manager");
        showManagerScreen();
      }
    }
  };

  xhttp.open("GET", `http://localhost:9001/ersapp/json/getcurrentuser`);
  xhttp.send();
}

function logOut() {
  let xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      console.log("readyState has become 4 AND the status code is 200");

      let currentUser = null;
      console.log("session ===>", currentUser);

      window.location.replace("http://localhost:9001/ersapp/login.html");
    }
  };

  xhttp.open("GET", `http://localhost:9001/ersapp/json/getcurrentuser`);
  xhttp.send();
}

function ourDOMManipulationCurrentUser(ourObjectFromJSON) {
  document.getElementById("welcomeText").innerText =
    "Welcome, " +
    ourObjectFromJSON.userFirstName +
    " " +
    ourObjectFromJSON.userLastName;
}

function changeTicketStatus() {
  let xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      console.log("readyState has become 4 AND the status code is 200");

      let ticket = JSON.parse(xhttp.responseText);

      ourDOMManipulationchangeTicketStatus(ticket);
      console.log(ticket);
    }
  };
  xhttp.open("GET", `http://localhost:9001/ersapp/json/denyorapprove`);
  xhttp.send();
}

function ourDOMManipulationchangeTicketStatus(ticket) {
  document.getElementById("change_ticket_status");
}

function ajaxCreateTicket() {
  let xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      console.log("readyState has become 4 AND the status code is 200");

      console.log("xhttp.responseText ===> ", xhttp.responseText);
      let ticket = JSON.parse(xhttp.responseText);
      console.log("ticket ===> ", ticket);

      ourDOMManipulationCreateTicket(ticket);
      console.log(ticket);
    }
  };

  xhttp.open("GET", `http://localhost:9001/ersapp/json/create`);
  xhttp.send();
}

function ourDOMManipulationCreateTicket(ticket) {
  document.getElementById("create_ticket");
  // updateTableWithNewTicket(ticket);
}

function ajaxGetTicketsByEmployee(currentUser) {
  let xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      console.log("readyState has become 4 AND the status code is 200");

      let ticketsByEmployee = JSON.parse(xhttp.responseText);

      ourDOMManipulationTicketsByEmployee(ticketsByEmployee);
      console.log(ticketsByEmployee);
    }
  };

  xhttp.open("GET", `http://localhost:9001/ersapp/json/employeetickets`);
  xhttp.send();
}

function ourDOMManipulationTicketsByEmployee(ourObjectFromJSON) {
  for (let i = 0; i < ourObjectFromJSON.length; i++) {
    //creating elements
    let newTr = document.createElement("tr");
    let newTh = document.createElement("th");

    let newTd1 = document.createElement("td");
    let newTd2 = document.createElement("td");
    let newTd3 = document.createElement("td");
    let newTd4 = document.createElement("td");
    let newTd5 = document.createElement("td");
    let newTd6 = document.createElement("td");
    let newTd7 = document.createElement("td");
    let newTd8 = document.createElement("td");
    let newTd9 = document.createElement("td");
    let newTd10 = document.createElement("td");

    //populating data
    newTh.setAttribute("scope", "row");
    let newTextTh = document.createTextNode(ourObjectFromJSON[i].reimbId);
    let newTextD1 = document.createTextNode(ourObjectFromJSON[i].reimbAmount);
    let newTextD2 = document.createTextNode(
      ourObjectFromJSON[i].reimbSubmitted
    );
    let newTextD3 = document.createTextNode(ourObjectFromJSON[i].reimbResolved);
    let newTextD4 = document.createTextNode(
      ourObjectFromJSON[i].reimbDescription
    );
    let newTextD5 = document.createTextNode(ourObjectFromJSON[i].reimbReceipt);
    let newTextD6 = document.createTextNode(ourObjectFromJSON[i].reimbAuthor);
    let newTextD7 = document.createTextNode(ourObjectFromJSON[i].reimbResolver);
    let newTextD8 = document.createTextNode(ourObjectFromJSON[i].reimbStatusId);
    let newTextD9 = document.createTextNode(ourObjectFromJSON[i].reimbTypeId);

    // appending
    newTh.appendChild(newTextTh);
    newTd1.appendChild(newTextD1);
    newTd2.appendChild(newTextD2);
    newTd3.appendChild(newTextD3);
    newTd4.appendChild(newTextD4);
    newTd5.appendChild(newTextD5);
    newTd6.appendChild(newTextD6);
    newTd7.appendChild(newTextD7);
    newTd8.appendChild(newTextD8);
    newTd9.appendChild(newTextD9);

    newTr.appendChild(newTh);
    newTr.appendChild(newTd1);
    newTr.appendChild(newTd2);
    newTr.appendChild(newTd3);
    newTr.appendChild(newTd4);
    newTr.appendChild(newTd5);
    newTr.appendChild(newTd6);
    newTr.appendChild(newTd7);
    newTr.appendChild(newTd8);
    newTr.appendChild(newTd9);

    let newSelection = document.querySelector("#ticketsTableBody");
    newSelection.appendChild(newTr);
  }
}

function ajaxGetAllTickets() {
  let xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      console.log("readyState has become 4 AND the status code is 200");

      let tickets = JSON.parse(xhttp.responseText);
      console.log(tickets);

      ourDOMManipulationAllTickets(tickets);
    }
  };

  xhttp.open("GET", `http://localhost:9001/ersapp/json/alltickets`);
  xhttp.send();
}

function ourDOMManipulationAllTickets(ourObjectFromJSON) {
  for (let i = 0; i < ourObjectFromJSON.length; i++) {
    //creating elements
    let newTr = document.createElement("tr");
    let newTh = document.createElement("th");

    let newTd1 = document.createElement("td");
    let newTd2 = document.createElement("td");
    let newTd3 = document.createElement("td");
    let newTd4 = document.createElement("td");
    let newTd5 = document.createElement("td");
    let newTd6 = document.createElement("td");
    let newTd7 = document.createElement("td");
    let newTd8 = document.createElement("td");
    let newTd9 = document.createElement("td");
    newTd8.setAttribute('class', 'statusId');

    // let newTd10 = document.createElement("td");

    //populating data
    newTh.setAttribute("scope", "row");
    let newTextTh = document.createTextNode(ourObjectFromJSON[i].reimbId);
    let newTextD1 = document.createTextNode(ourObjectFromJSON[i].reimbAmount);
    let newTextD2 = document.createTextNode(
      ourObjectFromJSON[i].reimbSubmitted
    );
    let newTextD3 = document.createTextNode(ourObjectFromJSON[i].reimbResolved);
    let newTextD4 = document.createTextNode(
      ourObjectFromJSON[i].reimbDescription
    );
    let newTextD5 = document.createTextNode(ourObjectFromJSON[i].reimbReceipt);
    let newTextD6 = document.createTextNode(ourObjectFromJSON[i].reimbAuthor);
    let newTextD7 = document.createTextNode(ourObjectFromJSON[i].reimbResolver);
    let newTextD8 = document.createTextNode(ourObjectFromJSON[i].reimbStatusId);
    let newTextD9 = document.createTextNode(ourObjectFromJSON[i].reimbTypeId);

    // appending
    newTh.appendChild(newTextTh);
    newTd1.appendChild(newTextD1);
    newTd2.appendChild(newTextD2);
    newTd3.appendChild(newTextD3);
    newTd4.appendChild(newTextD4);
    newTd5.appendChild(newTextD5);
    newTd6.appendChild(newTextD6);
    newTd7.appendChild(newTextD7);
    newTd8.appendChild(newTextD8);
    newTd9.appendChild(newTextD9);

    newTr.appendChild(newTh);
    newTr.appendChild(newTd1);
    newTr.appendChild(newTd2);
    newTr.appendChild(newTd3);
    newTr.appendChild(newTd4);
    newTr.appendChild(newTd5);
    newTr.appendChild(newTd6);
    newTr.appendChild(newTd7);
    newTr.appendChild(newTd8);
    newTr.appendChild(newTd9);

    let newSelection = document.querySelector("#ticketsTableBody");
    newSelection.appendChild(newTr);
  }
}

function showEmployeeScreen(currentUser) {
  console.log("showEmployeeScreen has been called here");
  ajaxGetTicketsByEmployee(currentUser);
  // ajaxCreateTicket();
  document.getElementById("change_ticket_status").style.display = "none";
}

function showManagerScreen() {
  console.log("showManagerScreen has been called here");
  ajaxGetAllTickets();
  document.getElementById("create_ticket").style.display = "none";
  // changeTicketStatus();
}

function updateTicketStatus(checkbox) {
  var checkboxes = document.getElementsByName("reimbStatusId");
  checkboxes.forEach((item) => {
    if (item !== checkbox) item.checked = false;
  });
}

function updateTableWithNewTicket(ticket) {
  let newTr = document.createElement("tr");
  let newTh = document.createElement("th");

  let newTd1 = document.createElement("td");
  let newTd2 = document.createElement("td");
  let newTd3 = document.createElement("td");
  let newTd4 = document.createElement("td");
  let newTd5 = document.createElement("td");
  let newTd6 = document.createElement("td");
  let newTd7 = document.createElement("td");
  let newTd8 = document.createElement("td");
  let newTd9 = document.createElement("td");
  let newTd10 = document.createElement("td");

  //populating data
  newTh.setAttribute("scope", "row");
  let newTextTh = document.createTextNode(ourObjectFromJSON[i].reimbId);
  let newTextD1 = document.createTextNode(ourObjectFromJSON[i].reimbAmount);
  let newTextD2 = document.createTextNode(ourObjectFromJSON[i].reimbSubmitted);
  let newTextD3 = document.createTextNode(ourObjectFromJSON[i].reimbResolved);
  let newTextD4 = document.createTextNode(
    ourObjectFromJSON[i].reimbDescription
  );
  let newTextD5 = document.createTextNode(ourObjectFromJSON[i].reimbReceipt);
  let newTextD6 = document.createTextNode(ourObjectFromJSON[i].reimbAuthor);
  let newTextD7 = document.createTextNode(ourObjectFromJSON[i].reimbResolver);
  let newTextD8 = document.createTextNode(ourObjectFromJSON[i].reimbStatusId);
  let newTextD9 = document.createTextNode(ourObjectFromJSON[i].reimbTypeId);

  // appending
  newTh.appendChild(newTextTh);
  newTd1.appendChild(newTextD1);
  newTd2.appendChild(newTextD2);
  newTd3.appendChild(newTextD3);
  newTd4.appendChild(newTextD4);
  newTd5.appendChild(newTextD5);
  newTd6.appendChild(newTextD6);
  newTd7.appendChild(newTextD7);
  newTd8.appendChild(newTextD8);
  newTd9.appendChild(newTextD9);

  newTr.appendChild(newTh);
  newTr.appendChild(newTd1);
  newTr.appendChild(newTd2);
  newTr.appendChild(newTd3);
  newTr.appendChild(newTd4);
  newTr.appendChild(newTd5);
  newTr.appendChild(newTd6);
  newTr.appendChild(newTd7);
  newTr.appendChild(newTd8);
  newTr.appendChild(newTd9);

  document.querySelector("#ticketsTableBody").appendChild(newTr);
}
