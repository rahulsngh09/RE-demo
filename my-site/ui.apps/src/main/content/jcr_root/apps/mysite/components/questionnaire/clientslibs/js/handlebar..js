// const bikeTemplate = ` <div class="bike-cards">
//                         {{#each bikes}}
//                             <div style="height: 570px; background-color: #000; border: 2px solid grey; border-radius: 6px;">
//                                 <div>
//                                     <div class="bike-card">
//                                         <div class="nameAndImgDiv">
//                                             <h3 style="margin: 0px;">{{bikeName}}</h3>
//                                             <span><img src="{{forwardIcon}}" alt="Forward Icon"></span>
//                                         </div>
                                        
//                                         <div class="bike-image-container">
//                                             <div class="hero-image">
//                                                 <img src="{{bikeHeroImage}}" alt="{{bikeName}} Image" class="hero-image" width="289px" height="178px">
//                                             </div>
//                                         </div>
                                        
//                                         <p style="display: flex; justify-content: center;"><strong>Starts from ₹</strong> {{bikePrice}}</p>
//                                         <div class="image-thumbnails">
//                                             {{#each bikeSmallImages}}
//                                                 <img src="{{this}}" alt="Small Image">
//                                             {{/each}}
//                                         </div>
            
//                                         <div class="bike-info">
//                                             <ul class="bike-features">
//                                                 {{#each bikeFeatures}}
//                                                     <li>{{this}}</li>
//                                                 {{/each}}
//                                             </ul>
//                                         </div>
//                                     </div>
            
//                                     <div class="actions">
//                                         <button class="configure-btn prev">CONFIGURE NOW</button>
//                                         <button class="test-ride-btn" id="book-a-test-ride-{{@index}}" style="
//                                             display: flex;
//                                             height: 40px;
//                                             width: 204px;
//                                             padding: 8px 16px;
//                                             cursor: pointer;
//                                             align-items: center;
//                                             justify-content: center;
//                                             font-size: 12px;
//                                             font-weight: 700;
//                                         ">BOOK A TEST RIDE</button>
//                                     </div>
//                                 </div>
//                             </div>
//                         {{/each}}
//                     </div>`;