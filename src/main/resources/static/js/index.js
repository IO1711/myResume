function getProject(projectId) {
    $.ajax({
        type: 'GET',
        url: '/getProject/' + projectId, // Assume this endpoint returns the project details
        success: function(data) {
            displayProject(data);
        },
        error: function(err) {
            console.error("Error fetching project", err);
        }
    });
}

function displayProject(project) {
    // Assuming your project object contains a 'downloadUrl' for the image
    const projectContainer = document.getElementById("imageContainer");
    
    const projectNameElement = document.getElementById("projectName");
    projectNameElement.textContent = project.name;

    const projectImageElement = document.createElement("img");
    projectImageElement.src = project.downloadUrl;
    projectImageElement.alt = project.name;
    projectImageElement.classList = "images";

    
    projectContainer.appendChild(projectImageElement);
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
                descriptionColumn.colSpan = 2;
                descriptionColumn.textContent = project.projectDescription;

                firstRow.appendChild(descriptionColumn);

                
                projecttbody.appendChild(firstRow);
                let techCounter = 0;
                $.ajax({
                    type: 'GET',
                    url: '/getAllTechnologies',
                    data: {projectId : project.id},
                    success: function(data){
                        const techJson = JSON.parse(data);

                        techJson.forEach(tech => {
                            let techRow = document.createElement("tr");

                            let emptyCell = document.createElement("td");
                            let techCell = document.createElement("td");
                            techCell.textContent = "- "+tech.technology;
                            techRow.appendChild(emptyCell);
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
