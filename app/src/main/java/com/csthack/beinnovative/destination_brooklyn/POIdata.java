package com.csthack.beinnovative.destination_brooklyn;

import com.csthack.beinnovative.destination_brooklyn.pointInterestClass;

/**
 * Created by nolan on 4/9/16.
 */
public class POIdata {
    private pointInterestClass[] POIobjects;

    POIdata() {
        pointInterestClass POI1 = new pointInterestClass(
                "Brooklyn Historical Society",
                "http://www.brooklynhistory.org/library/Othmer_Library.jpg",
                "The Brooklyn Historical Society (BHS), founded in 1863, is a museum, library, and educational center preserving and encouraging the study of Brooklyn's 400-year past. The society's Romanesque Revival building, designed by George B. Post and built in 1878-81, is a National Historic Landmark and part of New York City's Brooklyn Heights Historic District.",
                "http://www.brooklynhistory.org/",
                "1860",
                "Museum",
                40.694796,
                -73.992408
        );


        pointInterestClass POI2 = new pointInterestClass(
                "Brooklyn Children's Museum",
                "http://images.familyvacationcritic.com/article_slideshows/12c0495c5a8d24eb052e9872bef25186.jpg\n",
                "The Brooklyn Children's Museum is a children museum in Crown Heights, Brooklyn, New York City. Founded in 1899, it is the first museum in the United States – and according to some, the first one worldwide[1] – that caters specifically to children. It is unusual in its location in what is predominantly a residential area.[2] Housed in a multi-level underground gallery,[3] the museum underwent an expansion and renovation to double its space, reopened on September 20, 2008, and became the first green museum in New York City.",
                "http://www.brooklynkids.org/",
                "1890",
                "Museum",
                40.674407,
                -73.943920
        );

        pointInterestClass POI3 = new pointInterestClass(
                "Brooklyn War Memorial",
                "http://cadmanpark.org/cadmanwp/wp-content/uploads/2015/04/Memorial_FullSizeRender_smaller.jpg",
                "The memorial was dedicated on November 12, 1951 at an elaborate ceremony attended by elected officials and veterans groups. Though there were some 3,500 contributors, mainly from local businesses to the public subscription, the full plan was never realized due to a lack of funding. The scaled-back version of the memorial consists of a memorial hall with an honor roll listing the names of those who died serving during the war. The memorial was intended to be part of a larger plan to revitalize this area of Brooklyn, which included the Brooklyn Civic Center building, new municipal facilities, and expanded housing opportunities.",
                "http://www.nycgovparks.org/parks/cadman-plaza-park-and-brooklyn-war-memorial/monuments/177",
                "1950",
                "Memorial",
                40.698634,
                -73.990605
        );

        pointInterestClass POI4 = new pointInterestClass(
                "Brooklyn Borough Hall",
                "http://www.nyc.gov/html/dcas/images/photo_gallery/brook_boroughhall.jpg",
                "Brooklyn Borough Hall was designed by architects Calvin Pollard and Gamaliel King in the Greek Revival style, and constructed of Tuckahoe marble under the supervision of superintendent Stephen Haynes. It was completed in 1848 to be used as the City Hall of the former City of Brooklyn. In January 1898 the independent City of Brooklyn merged with the City of New York, and Kings County became the Borough of Brooklyn, at which time the building became Brooklyn Borough Hall.",
                "http://brooklyn-usa.org/",
                "1848",
                "Building",
                40.692861,
                -73.990083
        );

        pointInterestClass POI5 = new pointInterestClass(
                "Dumbo Wall",
                "http://dumbonyc.com/wp-content/uploads/2013/08/9422172487_f2073ca554_h-1024x680.jpg",
                "The DUMBO Improvement District and Two Trees Management Co in partnership with the New York City Department of Transportation Urban Art Program (NYCDOT) and the Jonathan LeVine Gallery, announce the creation of DUMBO Walls, a series of eight outdoor murals packed within a four-block stretch of DUMBO along the BQE.",
                "http://twotreesny.com/page/dumbo-walls",
                "2010",
                "Street Art",
                40.701509,
                -73.988623
        );

        pointInterestClass POI6 = new pointInterestClass(
                "The Bushwick Collective",
                "https://scontent-lga3-1.xx.fbcdn.net/hphotos-xal1/v/t1.0-9/12390918_927930820623394_6123209008974956384_n.jpg?oh=9fd89ad2d144301a1413ead2f4413760&oe=5776B6B5",
                "The Bushwick Collective begins on Jefferson Street and continues on Troutman Street towards Saint Nicholas Avenue. The accidental curator of the collective is Joseph Ficalora, a Native of Bushwick and Brooklyn.  In 1991, Joseph’s father, Ignazio Ficalora, was killed on the way home from the family steel fabrication business. He was murdered for a few dollars in his wallet and the gold chain he had around his neck. At the time Joseph was only twelve years old. A few years ago, in 2011, Joseph experienced another tragedy, the loss of his mother, who battled a brain tumor for four years.",
                "https://www.facebook.com/TheBushwickCollective",
                "2010",
                "Street Art",
                40.707628,
                -73.921809
        );

        POIobjects = new pointInterestClass[] {POI1, POI2, POI3, POI4, POI5, POI6};
    }
    /*
    pointInterestClass POI7 = new pointInterestClass(
            "",
            "",
            "",
            "",
            "",
            "",
            40.694796,
            -73.992408
    );
    */

    public void setPOIobjects (pointInterestClass obj) {
        for (int i =0; i<6; i++){
            POIobjects[i] = obj;
        }
    }

    public pointInterestClass[] getPOIobjects (){
        return POIobjects;
    }

}
