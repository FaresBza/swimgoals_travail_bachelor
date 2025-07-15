"use client"

import FormGroup from "./FormGroup";

import './../styles/BackgroundImage.scss'
import BackButton from "../components/BackButton";

const AddGroup = () => {

    return(
        <div className="container blur">
            <BackButton />
            <FormGroup />
        </div>
    )
}

export default AddGroup;