import Button from "./components/Button";
import Header from "./components/Header";

import './styles/Home.scss'
import './styles/BackgroundImage.scss'

export default function Home() {

  return (
    <div className="container">
      <Header />
      <main className="home-main">
        <div className="button-container">
          <Button txt="Connexion" />
          <Button txt="Inscription" />
        </div>
      </main>
    </div>
  )
}
