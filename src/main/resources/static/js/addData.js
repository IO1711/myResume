const dataForm = document.getElementById("addMyData");

if(dataForm){
    dataForm.addEventListener('submit', function(event){
        event.preventDefault();
        $("#submitButton").prop("disabled", true);
        
        const formData = new FormData(dataForm);

        $.ajax({
            type: 'POST',
            url: '/saveMyData',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data){
            },
            complete: function() {
                // Re-enable the submit button after request completion
                $("#submitButton").prop("disabled", false);
            }
        });
    });
}


const dataFormUpdate = document.getElementById("updateMyData");

if(dataFormUpdate){
    dataFormUpdate.addEventListener('submit', function(event){
        event.preventDefault();
        $("#submitButtonUpdate").prop("disabled", true);
        
        const formData = new FormData(dataFormUpdate);

        $.ajax({
            type: 'PUT',
            url: '/updateMyData',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data){
            },
            complete: function() {
                // Re-enable the submit button after request completion
                $("#submitButtonUpdate").prop("disabled", false);
            }
        });
    });
}

const hobbiesData = document.getElementById("setHobbies");

if(hobbiesData){
    hobbiesData.addEventListener('submit', function(event){
        event.preventDefault();
        $("#addHobbyButton").prop("disabled", true);

        const formData = new FormData(hobbiesData);

        $.ajax({
            type: 'POST',
            url: '/setHobbies',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data){
            },
            complete: function(){
                $("#addHobbyButton").prop("disabled", false);
            }
        });
    });
}

const projectData = document.getElementById("setProject");

if(projectData){
    
    projectData.addEventListener('submit', function(event){
        event.preventDefault();
        $("#projectButton").prop("disabled", true);

        const formData = new FormData(projectData);
        
        $.ajax({
            type: 'POST',
            url: '/saveProject',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data){
                
                console.log(data);
                
            },
            complete: function(){
                $("#projectButton").prop("disabled", false);
            }
        });
    });
}


function addTechnology(){
    const parentTech = document.getElementById("technologySet");

    let newInput = document.createElement("input");
    newInput.type = "text";
    newInput.name = "technology";

    parentTech.appendChild(newInput);
}

const contactData = document.getElementById("contacts");


if(contactData){
    contactData.addEventListener('submit', function(event){
        event.preventDefault();

        $("#contactButton").prop("disabled", true);

        const formData = new FormData(contactData);

        console.log(JSON.stringify(formData));

        console.log("Sending this: "+formData);
        $.ajax({
            type: 'POST',
            url: '/saveContact',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data){
                $("#contactButton").prop("disabled", false);
            }
        });
    });
}