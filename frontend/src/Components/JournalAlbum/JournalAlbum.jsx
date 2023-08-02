import "./JournalAlbum.css";
import { useEffect, useState } from "react";
import {
  Button,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Container,
  Grid,
  Typography,
  Icon,
  IconButton,
} from "@mui/material";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import { useNavigate } from "react-router-dom";

const JournalAlbum = () => {
  const [journalList, setJournalList] = useState([]);
  // const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    fetch("/api/v1/users/1/journals")
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setJournalList(result);
        //  setLoading(false);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from /api/journal:${error}`)
      );
  }, []);

  const handleRedirection = (id) => {
    navigate(`/${id}`);
  };

  const deleteJournal = (journalId) => {
    console.log(journalId);
    fetch(`/api/v1/journals/${journalId}`, {
      method: "DELETE",
    })
      .then((res) => {
        res.json();
        if (res.ok) {
          setJournalList((journalList) => {
            return journalList.filter((journal) => journal.id !== journalId);
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // if (loading) {
  //     return <Loading />;
  // }

  return (
    <>
      <Container sx={{ py: 8 }} maxWidth="md">
        <Grid container spacing={4}>
          {journalList.map((journal) => (
            <Grid item key={journal.id} xs={12} sm={6} md={4}>
              <Card
                sx={{
                  height: "100%",
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                <CardMedia
                  component="div"
                  sx={{
                    // 16:9
                    pt: "56.25%",
                  }}
                  image="https://images.pexels.com/photos/669986/pexels-photo-669986.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Typography
                    gutterBottom
                    variant="h5"
                    component="h2"
                    sx={{
                      fontFamily: "Satisfy",
                    }}
                  >
                    {journal.title}
                  </Typography>
                  <Typography>{journal.createdAt.split("T")[0]}</Typography>
                </CardContent>
                <CardActions
                  sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "space-between",
                  }}
                >
                  <Button
                    size="small"
                    onClick={() => handleRedirection(journal.id)}
                  >
                    Open
                  </Button>
                  <IconButton aria-label="Delete">
                    <DeleteForeverIcon
                      onClick={() => deleteJournal(journal.id)}
                    />
                  </IconButton>
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
    </>
  );
};

export default JournalAlbum;
