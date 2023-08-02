import "./ConfirmationDialog.css";
import {
  Box,
  Button,
  CardActions,
  Container,
  Stack,
  Typography,
  Dialog,
  DialogTitle,
} from "@mui/material";

const ConfirmationDialog = ({ open, onClose, itemTitle, onSubmit }) => {
  return (
    <>
      <Dialog onClose={onClose} open={open} onSubmit={onSubmit}>
        <DialogTitle>Are you sure to delete {itemTitle}?</DialogTitle>
        <CardActions
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-evenly",
          }}
        >
          <Button size="medium" onClick={() => onSubmit()}>
            DELETE
          </Button>
          <Button size="medium" onClick={() => onClose()}>
            CANCEL
          </Button>
        </CardActions>
      </Dialog>
    </>
  );
};

export default ConfirmationDialog;
