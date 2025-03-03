import './../styles/ButtonAuth.scss'

import React from 'react';

interface ButtonProps {
    txt: string;
}

const Button: React.FC<ButtonProps> = ({ txt }) => {
    return (
        <button className="button">
            {txt}
        </button>
    )
    
}

export default Button;