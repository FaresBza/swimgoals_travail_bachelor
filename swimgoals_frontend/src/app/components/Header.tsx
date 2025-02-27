import './../styles/Header.scss'

const Header = () => {
    return (
        <>
            <header className="home-header">
                <img src="images/Logo.svg" alt="SwimGoals Logo" className="logo"/>
                <h1 className="main-title">Suivez chaque progrès, plongez vers l&apos;excellence</h1>
            </header>
        </>
    );
}

export default Header;