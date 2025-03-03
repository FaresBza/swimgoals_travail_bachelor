import './../styles/ButtonAuth.scss'

import React from 'react';

interface ButtonProps {
    txt: string;
    onClick: () => void;
}

const Button: React.FC<ButtonProps> = ({ txt, onClick }) => {
    return (
        <button className="button" onClick={onClick}>
            {txt}
        </button>
    )
    
}

export default Button;