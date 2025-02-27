import Button from "./components/Button";
import Header from "./components/Header";

import './styles/Home.scss'
import './styles/BackgroundImage.scss'

export default function Home() {
  return (
    <div className="container">
      <Header />
      <div className="button-container">
        <Button />
      </div>
    </div>
  )
}
