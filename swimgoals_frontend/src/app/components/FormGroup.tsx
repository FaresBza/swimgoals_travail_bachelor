import React from "react";

interface FormGroupProp {
    name: string;
}

const FormGroup: React.FC<FormGroupProp> = () => {

    return(
        <div
            className="add-group-container"
            data-aos="fade-up"
            data-aos-duration="800"
        >
            <div className="title-container" aria-labelledby="main-title">
                <h1 className="title" id="main-title">Ajouter un groupe</h1>
            </div>
            <main className="main">
                <form
                            className="form"
                        >
                            <div className="form-group">
                                <label className="name-label" htmlFor="name">Name</label>
                                <input
                                    type="text"
                                    className="input"
                                    placeholder="Name"
                                    id="name"
                                    required
                                />
                            </div>
                        </form>
                        <button
                            type="submit" 
                            className="submit-button"
                        >
                            Se connecter
                        </button>
            </main>
        </div>
    )
}

export default FormGroup;