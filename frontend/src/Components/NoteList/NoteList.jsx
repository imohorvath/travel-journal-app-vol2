import "./NoteList.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import {
  Card,
  CardActions,
  CardContent,
  Container,
  Grid,
  Button,
  Typography,
} from "@mui/material";

const NoteList = () => {
  const { journalId } = useParams();
  const [noteList, setNoteList] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`/api/note/${journalId}/all`)
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setNoteList(result);
        setLoading(false);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from /api/journal:${error}`)
      );
  }, [journalId]);

  // if (loading) {
  //     return <Loading />;
  // }

  return (
    <>
      <Container sx={{ py: 8 }} maxWidth="md">
        <Grid container spacing={2}>
          {noteList.map((note) => (
            <Grid item xs={12} key={note.id}>
              <Card>
                <CardContent>
                  <Typography gutterBottom
                              sx={{
                                fontSize: 18,
                                fontFamily: 'Handlee'
                              }}
                  >
                    {note.text}
                  </Typography>
                  <Typography variant="body2" color="textSecondary">
                    {note.timestamp.split("T")[0]}
                  </Typography>
                </CardContent>
                <CardActions>
                  <Button size="small">Edit</Button>
                  <Button size="small">Remove</Button>
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
    </>
  );
};

export default NoteList;
