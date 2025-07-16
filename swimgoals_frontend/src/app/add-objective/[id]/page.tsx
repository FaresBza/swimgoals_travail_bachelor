"use client"

import FormObjective from './FormObjective';
import './../../styles/BackgroundImage.scss'
import BackButton from '@/app/components/BackButton';

const AddObjective = () => {
    return (
        <div className="container blur">
            <BackButton />
            <FormObjective />
        </div>
    )
}

export default AddObjective;