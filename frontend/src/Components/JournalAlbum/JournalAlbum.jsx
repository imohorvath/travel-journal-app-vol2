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
import ConfirmationDialog from "../ConfirmationDialog";
import { createClient } from "pexels";

const JournalAlbum = ({ journalList, refreshJournalList }) => {
  const [dialogOpen, setDialogOpen] = useState(false);
  const [currentJournal, setCurrentJournal] = useState("");
  const [imageURLs, setImageURLs] = useState({});
  // const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  const handleRedirection = (id) => {
    navigate(`/${id}`);
  };

  const handleDeleteClicked = (journal) => {
    console.log("handleDeleteClicked activated");
    setCurrentJournal(journal);
    console.log(journal);
    setDialogOpen(true);
  };

  const handleDialogClose = () => {
    console.log("handleDialogClose activated");
    setCurrentJournal("");
    console.log(currentJournal);
    setDialogOpen(false);
  };

  const handleDeleteConfirmed = () => {
    console.log("handleDeleteConfirmed activated");
    console.log(currentJournal);
    deleteJournal(currentJournal.id);
    setCurrentJournal("");
    setDialogOpen(false);
    console.log(currentJournal);
  };

  const deleteJournal = (journalId) => {
    console.log(journalId);
    fetch(`/api/v1/journals/${journalId}`, {
      method: "DELETE",
    })
      .then((res) => {
        console.log(res);
        if (!res.ok) {
          throw new Error("Network response was not ok.");
        }
        refreshJournalList(journalId);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getPexelImage = (keyword, journalId) => {
    const client = createClient(
      "ObPMaZKwS1wvcaQJAHUkbW9dryeMn2WgxOyIJCGH7NwMo0tRFpgcZgvx"
    );
    const query = keyword;

    client.photos
      .search({ query, per_page: 1 })
      .then((photo) => {
        const newImageURLs = {
          ...imageURLs,
          [journalId]: photo.photos[0].src.landscape
        };
        setImageURLs(newImageURLs);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from Pexel`)
      );
  };

  useEffect(() => {
    journalList.forEach((journal) => {
      getPexelImage(journal.title, journal.id);
    });
  }, [journalList]);

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
                  image={imageURLs[journal.id] || ""}
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
                  <IconButton
                    aria-label="Delete"
                    onClick={() => handleDeleteClicked(journal)}
                  >
                    <DeleteForeverIcon />
                  </IconButton>
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
        {dialogOpen && (
          <ConfirmationDialog
            open={dialogOpen}
            onClose={handleDialogClose}
            itemTitle={currentJournal.title}
            onSubmit={handleDeleteConfirmed}
          />
        )}
      </Container>
    </>
  );
};

export default JournalAlbum;
