import { ErrorHandler } from "@angular/core";
import { ToastrService } from "ngx-toastr";

export class AppErrorHandler implements ErrorHandler {
  constructor(private toastr: ToastrService) {}

  handleError(error) {
    this.toastr.error(
      "An unexpected error occured!",
      "Oops! It's Not You, It's Us!"
    );
    // console.log(error);
  }
}
