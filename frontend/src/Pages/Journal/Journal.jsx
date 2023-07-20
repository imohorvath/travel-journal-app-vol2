import NoteList from "../../Components/NoteList";
import "./Journal.css";

import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Container, Typography } from "@mui/material";

const Journal = () => {
  const [journal, setJournal] = useState({});
  const [loading, setLoading] = useState(true);
  const { journalId } = useParams();

  useEffect(() => {
    fetch(`/api/journal/${journalId}`)
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setJournal(result);
        setLoading(false);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from /api/journal:${error}`)
      );
  }, [journalId]);
  return (
    <>
    <Container 
    sx = {{
      backgroundImage: `url(${'https://images.pexels.com/photos/669986/pexels-photo-669986.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'})`,
      backgroundSize: 'cover',
      height: 100,
    }}
    >
      <Typography
        component="h4"
        variant="h4"
        align="center"
        color="text.primary"
        gutterBottom
      >
        {journal.title}
      </Typography>
      </Container>
      <NoteList />
    </>
  );
};

export default Journal;
