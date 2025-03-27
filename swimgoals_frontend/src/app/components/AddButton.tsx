"use client"

import Image from 'next/image';

import './../styles/AddButton.scss';

const AddButton = () => {
    return (
        <>
            <a className="add-button">
                <Image src="/icons/add.svg" alt="Add button" width={38} height={388} />
            </a>
        </>
    )
}

export default AddButton;