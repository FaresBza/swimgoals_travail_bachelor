import React, { useEffect, useState } from "react";
import AOS from 'aos';
import 'aos/dist/aos.css';

const FormGroup = () => {

    const [name, setName] = useState<string>("");

    useEffect(() => {
        AOS.init();
    })

    return(
        <div
            className="add-group-container"
            data-aos="fade-up"
            data-aos-duration="800"
        >
            <main className="main-container">
                <div className="add-group-card">
                    <h2 className="title-card">Ajouter un groupe</h2>
                    <form
                        className="form-add-group"
                    >
                        <label className="label-group" htmlFor="group-name">Nom</label>
                        <input
                            type="text"
                            id="group-name"
                            className="input-group"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="Triathlon"
                            required
                        />
                        <button type="submit">Ajouter</button>
                    </form>
                </div>
            </main>
        </div>
    )
}

export default FormGroup;