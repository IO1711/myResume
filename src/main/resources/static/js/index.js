function getMyData(){
    const loadingBox = document.querySelector(".loading-box");
    loadingBox.style.display = "block";

    const fullName = document.getElementById("fullName");
    const jobTitle = document.getElementById("jobTitle");
    const description = document.getElementById("description");
    var myAge = 0;

    $.ajax({
        type: 'GET',
        url: '/getMyAge',
        success: function(age){
            myAge = age;
        }
    });

    $.ajax({
        type: 'GET',
        url: '/getMyData',
        success: function(data){
            let myData = JSON.parse(data);

            
            
            fullName.innerHTML = myData.fname + " " + myData.lname + ", " + myAge;
            jobTitle.textContent = myData.jobTitle;
            description.textContent = myData.description;
            loadingBox.style.display = "none";
        }
    });

    $.ajax({
        type: 'GET',
        url: '/getContact',
        success: function(data){
            const contactInfo = JSON.parse(data);

            const phoneNumber = document.getElementById("phoneNumber");
            const email = document.getElementById("email");
            const telegram = document.getElementById("telegram");

            phoneNumber.textContent = contactInfo.phoneNumber;
            email.textContent = contactInfo.email;

            let linkTelegram = document.createElement("a");
            linkTelegram.href = contactInfo.telegram;
            linkTelegram.textContent = "Bilolbek17";
            
            telegram.appendChild(linkTelegram);
        }
    });
}

function getAllProjects(){
    const projects = document.getElementById("projects");

    $.ajax({
        type: 'GET',
        url: '/getAllProjects',
        success: function(data){
            const dataJson = JSON.parse(data);

            dataJson.forEach(project => {
                //craeting container
                const projectContainer = document.createElement("div");
                projectContainer.classList = "projectContainer";
                projects.appendChild(projectContainer);

                //creating span name element
                const projectName = document.createElement("span");
                projectName.textContent = project.projectName;
                projectName.classList = "projectName";
                projectContainer.appendChild(projectName);

                //creating table
                const projectTable = document.createElement("projectTable");
                projectTable.classList = "projectTable";

                //creating tbody
                const projecttbody = document.createElement("tbody");
                projecttbody.classList = "projecttbody";
                projectTable.appendChild(projecttbody);

                projectContainer.appendChild(projectTable);

                //creating first row
                let firstRow = document.createElement("tr");

                let descriptionColumn = document.createElement("td");
                //descriptionColumn.colSpan = 4;
                descriptionColumn.textContent = project.projectDescription;

                

                

                

                firstRow.appendChild(descriptionColumn);
                

                let secondRow = document.createElement("tr");

                let linkCell = document.createElement("td");
                //linkCell.colSpan = 4;

                let pElement = document.createElement("span");
                pElement.textContent = "Link: ";

                let projectLink = document.createElement("a");
                projectLink.href = project.projectLink;
                projectLink.textContent = project.projectName;

                secondRow.appendChild(pElement);
                secondRow.appendChild(projectLink);


                
                projecttbody.appendChild(firstRow);
                projecttbody.appendChild(secondRow);
                let techCounter = 0;
                $.ajax({
                    type: 'GET',
                    url: '/getAllTechnologies',
                    data: {projectId : project.id},
                    success: function(data){
                        const techJson = JSON.parse(data);

                        techJson.forEach(tech => {
                            let techRow = document.createElement("tr");

                            //let emptyCell = document.createElement("td");
                            
                            let techCell = document.createElement("td");
                            techCell.textContent = "- "+tech.technology;
                            techCell.style.paddingLeft = '4%';
                            //emptyCell.innerHTML = "&nbsp;";
                            
                            //techRow.appendChild(emptyCell);
                            techRow.appendChild(techCell);

                            projecttbody.appendChild(techRow);
                            techCounter++;
                        })
                    },
                    error: function(err) {
                        console.error("Error fetching technologies", err);
                    }
                });

                let imageContainer = document.createElement("td");
                imageContainer.rowSpan = techCounter;
                imageContainer.classList = "imageContainer";

                const projectImageElement = document.createElement("img");
                projectImageElement.src = project.downloadUrl;
                projectImageElement.alt = project.projectName;
                projectImageElement.classList = "images";

                imageContainer.appendChild(projectImageElement);
                firstRow.appendChild(imageContainer);


            });
        },
        error: function(err) {
            console.error("Error fetching projects", err);
        }
    })
}

function flipCard(){
    const flipContainer = document.querySelectorAll(".flipContainer");
    
    flipContainer.forEach(container =>{
        container.classList.toggle('flipped');
    });
    

    const intButtons = document.querySelectorAll(".interfaceButton");
    intButtons.forEach(element =>{
        element.classList.toggle('inactiveIntButton');
    });
}
