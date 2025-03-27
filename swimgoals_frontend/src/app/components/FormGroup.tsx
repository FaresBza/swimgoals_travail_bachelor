import React, { useState } from "react";

const FormGroup = () => {

    const [name, setName] = useState<string>("");

    return(
        <div
            className="add-group-container"
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