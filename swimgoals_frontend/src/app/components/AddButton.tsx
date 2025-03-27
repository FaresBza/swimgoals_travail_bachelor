"use client"

import React from 'react';
import './../styles/AddButton.scss';

interface AddGroupButtonProp {
    onClick: () => void;
}

const AddButton: React.FC<AddGroupButtonProp> = ({onClick}) => {
    return (
        <>
            <a className="add-button" onClick={onClick}>
                <img src="/icons/add.svg" />
            </a>
        </>
    )
}

export default AddButton;