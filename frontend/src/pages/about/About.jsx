import React from "react";
import aboutMainImg from "assets/img/about_main.jpg";
import aboutSecondImg from "assets/img/about_1.jpg";
import aboutThirdImg from "assets/img/about_2.jpg";
import "./About.css";
import NavigationCard from "components/navigation-card/NavigationCard";

function About() {
  return (
    <>
      <NavigationCard
        name="About Us"
        link="HOME"
        subLink="About Us"
        linkTo="/"
      />
      <div className="container">
        <h1 className="about-title">About Us</h1>
        <div className="content">
          <div className="text-content">
            <p className="about-p">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla
              lobortis, quam ut faucibus bibendum, nisl leo tincidunt risus, nec
              fringilla est justo in nisl. Ut elementum pellentesque
              sollicitudin. Suspendisse potenti. Quisque vehicula nisi id leo
              ullamcorper posuere. Donec vitae aliquam magna, id luctus leo.{" "}
              <br></br>Vivamus mollis urna condimentum ipsum luctus luctus.
              Etiam in urna condimentum, dictum felis vitae, finibus sem. Duis
              ac nunc felis. Aliquam ut egestas arcu. Cras pretium diam elit,
              vitae condimentum tellus accumsan vestibulum. Fusce ultrices, nibh
              ut convallis sodales, turpis dolor scelerisque sem, at aliquam
              tortor eros ac nisi. <br></br> <br></br>Quisque volutpat nibh ut
              sodales imperdiet. Aenean massa ligula, suscipit non feugiat
              egestas, bibendum id dolor. Nulla finibus erat vitae mauris
              malesuada auctor. Suspendisse quis tellus vel metus euismod semper
              eu et purus. Phasellus interdum varius ligula nec molestie. Duis
              hendrerit, lorem quis convallis convallis, lorem felis faucibus
              ex, a euismod lectus lacus at erat. Donec euismod odio a suscipit
              semper. Nulla egestas egestas dapibus. <br></br> <br></br>Maecenas
              gravida ultrices odio ut rutrum. Nam bibendum nulla vulputate
              tortor elementum eleifend. Proin laoreet mollis erat, ut dignissim
              leo molestie sed. Sed a leo eget sapien sodales hendrerit sed
              elementum libero. Donec laoreet nisl eget erat commodo ullamcorper
              et vitae augue. In commodo maximus turpis, eu ullamcorper arcu
              molestie vel.
            </p>
          </div>
          <div className="images">
            <div className="main-picture">
              <img src={aboutMainImg} alt="about-main" />
            </div>
            <div className="sub-images">
              <img src={aboutSecondImg} alt="about-second" />
              <img src={aboutThirdImg} alt="about-third" />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default About;
