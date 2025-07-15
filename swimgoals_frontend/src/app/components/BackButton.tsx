"use client"
import Image from 'next/image';
import { useRouter } from 'next/navigation';

const BackButton = () => {

    const route = useRouter();

    return (
        <>
            <Image
                src="/icons/back-button.svg"
                alt="Back button"
                width={56}
                height={56}
                onClick={route.back}
                style={{margin: "10px 0 0 10px"}}
            />
        </>
    )
}

export default BackButton;