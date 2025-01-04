import { hotels } from './../datas/hotelsDetails.js';

function getQueryParams() {
   const params = new URLSearchParams(window.location.search);
   const ids = ['0','1', '2', '3', '4', '5']
   return ids.includes(params.get('id')) ? params.get('id') : 0;

}

const id = getQueryParams();

const hotelDetails = hotels[id].details;

const imageList = [hotelDetails.images]
const images = imageList[0]

const mainImage = document.getElementById('mainimage');
const hotelImage = document.querySelector('.hotel-images');
const sideImage = document.getElementById('side-image');


mainImage.src = images[1];


const sideImage1 = document.createElement('img');
sideImage1.src = images[2];
sideImage1.className = "sideImage";
sideImage1.alt = "sideImage"

const sideImage2 = document.createElement('img');
sideImage2.src = images[3];
sideImage2.className = "sideImage";
sideImage2.alt = "sideImage"

const sideImage3 = document.createElement('img');
sideImage3.src = images[4];
sideImage3.className = "sideImage";
sideImage3.alt = "sideImage"

const totalImages = Math.floor((Math.random()+1)*140);



const smallImagesBox = document.createElement('div');
smallImagesBox.className = "smallImageBox";

for(let i=5;i<=8;i++){
   const smallImage = document.createElement('img');
   smallImage.src = `${images[i]}`;
   smallImage.alt = "Hotel Image" + i;
   smallImage.classList = "smallImage";
   smallImagesBox.appendChild(smallImage);
}

sideImage.appendChild(sideImage1);
sideImage.appendChild(sideImage2);
sideImage.appendChild(sideImage3);
sideImage.appendChild(smallImagesBox);

const imageCover = document.createElement('div');
imageCover.className = "imageCover";

const moreImages = document.createElement('p');
moreImages.innerText = `+${totalImages}`;
moreImages.className = "moreImage";


smallImagesBox.appendChild(imageCover);
smallImagesBox.appendChild(moreImages);


const headersBox = document.getElementById('headers');

const hotelName = document.createElement('h1');
hotelName.innerText = hotels[id].name;
hotelName.className = "hotelName2";

headersBox.appendChild(hotelName);

const hotelDescBox = document.createElement('div');
hotelDescBox.className = 'hotelDescBox';

const hotelDesc = document.createElement('p');
hotelDesc.innerText = hotelDetails.desc;
hotelDesc.className = "hotelDesc";
hotelDescBox.appendChild(hotelDesc);

const highlights = document.createElement('div');
highlights.className ='highlights';

hotelDetails.highlights.forEach(features => {
   const highlight = document.createElement('div');
   highlight.className = "featuresHighlights";

   const checkimage = document.createElement('img');
   checkimage.src = "../assets/icons/checkgreen.svg";
   checkimage.alt = "Check Green";
   checkimage.className = 'CheckGreen'

   const feature = document.createElement('p');
   feature.className = 'feature';
   feature.innerText = features; 

   highlight.appendChild(checkimage);
   highlight.appendChild(feature);
   highlights.appendChild(highlight);
})

hotelDescBox.appendChild(highlights);
headersBox.appendChild(hotelDescBox);

const RatingBox = document.getElementById('ratingBox');

const ratings = hotelDetails.rating.split(" ");

const starbox = document.createElement('div');
starbox.className = "starBox";

const star  = document.createElement('img');
star.src = "../assets/icons/star.svg"
star.alt = "star";
star.className = "star";

const rate = document.createElement('p');
rate.innerText = ratings[0];
rate.className = "rating";
starbox.appendChild(rate);
starbox.appendChild(star);


const ranking = document.createElement('p');
ranking.innerText = ratings[1];
ranking.className = "ranking";
RatingBox.appendChild(starbox);
RatingBox.appendChild(ranking)

const fullReview = document.querySelector('.fullReview');
fullReview.href = hotelDetails.reviewLinks;

const amenities = document.querySelector('.popularAmenities');

const amenitiesBox = document.createElement('div');
amenitiesBox.className = 'amenitiesBox';

hotelDetails.facilities.forEach(facility => {
   const amenityBox = document.createElement('div');
   amenityBox.className = 'amenityBox';
   amenityBox.innerHTML = `<img src="../assets/icons/circleorange.svg" alt="orange Circle"></img><p class="amentiy">${facility}</p>`;
   amenitiesBox.appendChild(amenityBox);
});
amenities.appendChild(amenitiesBox);


const hotelLocation = document.querySelector('.locations');
const hotelMap = document.querySelector('.hotelmap');
hotelMap.innerHTML = `${hotelDetails.embedCode}<div class="locationDetails"><p class="hotelLocation">${hotelDetails.location}</p><a href=${hotelDetails.reviewLinks} class="seeMore">See More</a></div>`;

const nearByLocations = document.querySelector('.nearbyLocation');

hotelDetails.nearbyLocations.forEach(location =>{
   const locationBox  = document.createElement('div');
   locationBox.className = "locationBox";

   const locationName = document.createElement('p');
   locationName.innerText = location.name;
   locationName.className = 'locationName';

   const hypen = document.createElement('p');
   hypen.innerText = "-";
   hypen.className = "hypen";

   const locationDistance = document.createElement('p');
   locationDistance.innerText = location.distance;
   locationDistance.className = 'locationDistance';

   locationBox.appendChild(locationName);
   locationBox.appendChild(hypen);
   locationBox.appendChild(locationDistance);

   nearByLocations.appendChild(locationBox)
})

const roomDetailsBox = document.getElementById('roomDetailsBox');

const hotelsRooms = hotelDetails.rooms;

hotelsRooms.forEach((room)=>{
   let roomdetail = document.createElement('div');
   roomdetail.className = 'roomdetail';


   let roomImage = document.createElement('img');
   roomImage.src = room.image;
   roomImage.alt = room.name;
   roomImage.className = 'roomImage';

   let offer = document.createElement('p');
   offer.className = "roomOffer";
   offer.innerText = `${room.offer} Off`;

   roomdetail.appendChild(roomImage);
   roomdetail.appendChild(offer);


   let featureBox = document.createElement('div');
   featureBox.className = 'featureBox';

   let features = document.createElement('div');
   features.className = "roomFeaturesBox";

   room.features.forEach((feature,index)=>{
      let roomFeature = document.createElement('div');
      roomFeature.className = 'roomFeature';
      roomFeature.innerHTML = `<img src="../assets/icons/${room.icons[index]}.svg" alt="${room.icons[index]}" class="icons"/><p>${feature}</p>`
      features.appendChild(roomFeature);
   });

   const seeMore = document.createElement('p');
   seeMore.className = "seeMore";
   seeMore.innerHTML = `<a class="seeMore" href=${hotelDetails.reviewLinks}><p>See More</p><img src="../assets/icons/arrowright.svg"/></a>`;   

   const roomPrice = document.createElement('p');
   roomPrice.className = "roomPrice";
   roomPrice.innerText = `₹${room.price}`;

   const reserveNow = document.getElementById('reserveNow');
   reserveNow.href= hotelDetails.reviewLinks;
   reserveNow.style.textDecoration = "none"

   const reserveHotel = document.getElementById('reserveHotel');
   reserveHotel.href= hotelDetails.reviewLinks;
   reserveHotel.style.textDecoration = "none"

   const reserveBtn = document.createElement('a');
   reserveBtn.className = "reserveBtn";
   reserveBtn.innerText = "Reserve";
   reserveBtn.href=hotelDetails.reviewLinks;
   reserveBtn.style.textDecoration = "none"

   featureBox.appendChild(features);
   featureBox.appendChild(seeMore);
   featureBox.appendChild(roomPrice);
   featureBox.appendChild(reserveBtn);
   roomdetail.appendChild(featureBox);

   roomDetailsBox.appendChild(roomdetail)

})

const dropdowns = document.querySelectorAll('.dropdown');

dropdowns.forEach(dropdown => {
    const filterBox = dropdown.querySelector('.filter');
    const arrowIcon = dropdown.querySelector('.arrowIcon');
    const dropdownContent = dropdown.querySelector('.dropdownContent');
    const selectedText = dropdown.querySelector('.selectedText');
    const checkboxes = dropdown.querySelectorAll('.checkboxItem');
    
    let selectedItems = [];
    

    const toggleDropdown = () => {
        dropdownContent.classList.toggle('active');
        arrowIcon.classList.toggle('active');
    };


    const handleSelection = (e) => {
        const item = e.target.id;
        
        selectedItems = item;

        selectedText.innerText = selectedItems.length <= 0
            ? dropdown.getAttribute('data-type') 
            : selectedItems;
    };
    
    filterBox.addEventListener('click', toggleDropdown);
    checkboxes.forEach(checkbox => checkbox.addEventListener('click', handleSelection));
});




const overview = document.querySelector('.overview');
const booking = document.querySelector('.booking');

const overviewAmenities = document.querySelector('.overview-Amenties');

const roomAccessibility = document.querySelector('.rooms-accessiblity');




const overviewActive = ()=>{
  if (booking.classList.contains('active')){
      booking.classList.remove('active');
      roomAccessibility.classList.remove('active');
      overview.classList.add('active');
      overviewAmenities.classList.add('active');
}
}

const bookingActive = ()=>{
   if (overview.classList.contains('active')){
       overviewAmenities.classList.remove('active');
       overview.classList.remove('active');
       booking.classList.add('active');
       roomAccessibility.classList.add('active');
 }
 }

overview.addEventListener("click", overviewActive);
booking.addEventListener("click", bookingActive);



const accessibilies = document.querySelector('.accessibility');
const commonAreas = document.querySelector('.commonarea');

accessibilies.innerHTML=`<h1>Accessibilty</h1><p>${hotelDetails.accessibility}</p>`;

const areasBox = document.createElement('ul');
areasBox.className = "areaBox";

hotelDetails.commonAreas.forEach(area =>{
   let areas = document.createElement('li');
   areas.className = "hotelCommon-area";
   areas.innerText = area;
   areasBox.appendChild(areas);
})

commonAreas.appendChild(areasBox);



const hotelsList = document.getElementById('hotelsList1');


hotels.forEach((hotel,index) => {
   const hoteldiv = document.createElement('a');
   hoteldiv.className = 'hoteldiv'
   hoteldiv.href = `hotels.html?id=${index}`

   const hotelImage = document.createElement('img');
   hotelImage.className = 'hotelImage';
   hotelImage.src = hotel.image;
   hotelImage.alt = hotel.name;

   const hotelName = document.createElement('h1');
   hotelName.className = 'hotelName';
   hotelName.innerText = hotel.name;

   const hotelLocation = document.createElement('p');
   hotelLocation.innerText = hotel.location;
   hotelLocation.className = 'hotelLocation';


   const hotelReviewBox = document.createElement('div');
   hotelReviewBox.className = 'hotelReviewBox';


   const hotelRatings = document.createElement('div');
   hotelRatings.className = 'hotelRatings';

   const totalStars = hotel.ratings;
   for(let  i=0; i< totalStars; i++ ){
       const hotelStars = document.createElement('img');
       hotelStars.src = '../assets/icons/orangestar.svg';
       hotelStars.alt = 'stars';
       hotelRatings.appendChild(hotelStars);
   }

   const hotelReviews = document.createElement('p');
   hotelReviews.className = 'hotelReviews';
   hotelReviews.innerText = `(${hotel.reviews} Reviews)`;

   hotelReviewBox.appendChild(hotelRatings);
   hotelReviewBox.appendChild(hotelReviews);




   hoteldiv.appendChild(hotelImage);
   hoteldiv.appendChild(hotelName);
   hoteldiv.appendChild(hotelLocation);
   hoteldiv.appendChild(hotelReviewBox);


   hotelsList.appendChild(hoteldiv);
});


