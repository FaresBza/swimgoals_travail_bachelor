
const FormAuth = () => {

    return (
        <div
                className="register-container"
                data-aos="fade-up"
                data-aos-duration="500"
            >
                <div className="title-container">
                    <h1 className="title">Inscription</h1>
                </div>
                <main>
                    <div className="role-buttons">
                        <button></button>
                        <button></button>
                        <button></button>
                    </div>
                    <form
                        className="form"
                    >
                        <div className="form-group">
                            <label>Nom</label>
                            <input
                                type="text"
                                placeholder="Nom"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Prénom</label>
                            <input
                                type="text"
                                placeholder="Prénom"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Adresse mail</label>
                            <input
                                type="email"
                                placeholder="Email"
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Mot de passe</label>
                            <input
                                type="password"
                                placeholder="Mot de passe"
                                required
                            />
                        </div>
                        <button type="submit" className="submit-button">S&apos;inscrire</button>
                    </form>
                </main>
            </div>
    )
}

export default FormAuth;