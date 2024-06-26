function addCareerField() {
    var careerDiv = document.createElement('div');
    careerDiv.classList.add('career-field');
    careerDiv.innerHTML = `
        <input type="text" name="career" required>
        <button type="button" onclick="removeCareerField(this)">-</button>
    `;
    document.getElementById('career-fields').appendChild(careerDiv);
}

function removeCareerField(button) {
    var careerDiv = button.parentElement;
    document.getElementById('career-fields').removeChild(careerDiv);
}

document.addEventListener('DOMContentLoaded', function () {
    const checkboxes = document.querySelectorAll('.salary-options input[type="checkbox"]');
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            if (this.checked) {
                checkboxes.forEach(box => {
                    if (box !== this) {
                        box.checked = false;
                    }
                });
            }
        });
    });
});

function execDaumPostcode() {
    var elementLayer = document.getElementById('layer');
    var addressContainer = document.getElementById('layerContainer');

    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }
            document.getElementById('jobzone').value = addr;
            elementLayer.style.display = 'none';
        },
        width: '100%',
        height: '100%'
    }).embed(addressContainer);

    elementLayer.style.display = 'block';
    initLayerPosition();
}

function closeDaumPostcode() {
    var elementLayer = document.getElementById('layer');
    elementLayer.style.display = 'none';
}

function initLayerPosition() {
    var width = 300;
    var height = 400;
    var borderWidth = 2;
    var elementLayer = document.getElementById('layer');
    elementLayer.style.width = width + 'px';
    elementLayer.style.height = height + 'px';
    elementLayer.style.border = borderWidth + 'px solid';
    elementLayer.style.left = ((window.innerWidth - width) / 2 - borderWidth) + 'px';
    elementLayer.style.top = ((window.innerHeight - height) / 2 - borderWidth) + 'px';
}
