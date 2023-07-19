import "./Journals.css";
import {useEffect, useState} from "react";

const Journals = () => {
    const [journalList, setJournalList] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch("/api/journal")
            .then((res) => res.json())
            .then((result) => {
                setJournalList(result);
                setLoading(false);
            })
            .catch((error) =>
                console.log(`An error occurred at fetching from /api/journal:${error}`)
            );
    }, [])

    // if (loading) {
    //     return <Loading />;
    // }

    return (
        <div>
            <h1>Hello Journal!</h1>
        </div>
    );

}

export default Journals;