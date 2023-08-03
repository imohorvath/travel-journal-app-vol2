import "./JournalAlbumIntro.css";
import {
  Box,
  Button,
  Container,
  Stack,
  Typography,
} from "@mui/material";

const JournalAlbumIntro = ({ onShowCreate }) => {
 
  return (
    <>
      <Box
        sx={{
          bgcolor: "background.paper",
          pt: 6,
          pb: 6,
        }}
      >
        <Container maxWidth="sm">
          {/* <Typography
            component="h3"
            variant="h3"
            align="center"
            color="text.primary"
            gutterBottom
          >
            Your Journals
          </Typography> */}
          <Typography
            variant="h5"
            align="center"
            color="text.secondary"
            paragraph
          >
            Capture the essence of your travel experiences with our journals.
            Revisit cherished memories, add vibrant photos, and share your
            adventures with friends, all in one convenient space.
          </Typography>
          <Stack
            sx={{ pt: 4 }}
            direction="row"
            spacing={2}
            justifyContent="center"
          >
            <Button variant="contained" onClick={onShowCreate}>Create new Journal</Button>
          </Stack>
        </Container>
      </Box>
    </>
  );
};

export default JournalAlbumIntro;
