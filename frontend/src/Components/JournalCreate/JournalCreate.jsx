import "./JournalCreate.css";
import { useEffect, useState } from "react";
import {
  Autocomplete,
  FormControl,
  Typography,
  TextField,
  Container,
  CardContent,
  Card,
  CardActions,
  Button,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

const JournalCreate = ({ onCancel, onSubmit, userId }) => {
  const [allUsers, setAllUsers] = useState([]);
  const [title, setTitle] = useState("");
  const [contributors, setContributors] = useState([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    fetch("/api/v1/users/")
      .then((res) => res.json())
      .then((result) => {
        const filtered = result.filter((user) => user.id !== userId);
        setAllUsers(filtered);
        setLoading(false);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from /api/v1/users:${error}`)
      );
  }, [userId]);

  const handleSave = (e) => {
    e.preventDefault();

    const newJournal = {
      title: title,
      contributorIds: contributors.map((contributor) => contributor.id),
    };

    onSubmit(newJournal, userId);
  };

  // if (loading) {
  //     return <Loading />;
  //}

  return (
    <>
      <Container>
        <Card>
          <CardContent>
            <Typography variant="h5">Create a new Journal</Typography>
            <FormControl fullWidth sx={{ mt: 2 }}>
              <TextField
                label="Title"
                variant="outlined"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
            </FormControl>
            <FormControl fullWidth sx={{ mt: 2 }}>
              <Autocomplete
                multiple
                id="contributors-autocomplete"
                options={allUsers}
                getOptionLabel={(user) => user.username}
                value={contributors}
                onChange={(e, value) => setContributors(value)}
                renderInput={(params) => (
                  <TextField
                    {...params}
                    label="Contributors"
                    variant="outlined"
                  />
                )}
              />
            </FormControl>
          </CardContent>
          <CardActions>
            <Button onClick={handleSave} variant="contained" color="primary">
              Save
            </Button>
            <Button onClick={onCancel} variant="contained" color="secondary">
              Cancel
            </Button>
          </CardActions>
        </Card>
      </Container>
    </>
  );
};

export default JournalCreate;
