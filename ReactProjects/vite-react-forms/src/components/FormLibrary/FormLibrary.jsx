import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import "./FormLibrary.css";

function FormLibrary() {
  const {
    register,
    handleSubmit,
    watch,
    reset,
    formState: { errors },
  } = useForm({mode:'onChange'});

  const onSubmit = (data) => {
    console.log(data)
    reset()
  };

  /*const validateName = (value)=>{
    if(value === 'admin') {
      return 'Admin is not allowed'
    }
    return true;
  }*/

  const existingUsernames = ['admin', 'user', 'julio']
  const checkIfUsernameExists = async (username)=>{
    await new Promise(resolve => setTimeout(resolve, 1000))
    return existingUsernames.includes(username);
  }

  const watchedName = watch("name");
  const watchedEmail = watch("email");

  useEffect(() => {
    console.log("Name Changed:", watchedName);
  }, [watchedName]);
  useEffect(() => {
    console.log("Email Changed:", watchedEmail);
  }, [watchedEmail]);
  return (
    <div>
      <h1>Forms in React</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>
          Name:
          {/*<input type="text" {...register('name',{required:true, minLength:2})}/>*/}
          <input
            type="text"
            {...register("name", {
              required: "Name is required",
              minLength: {
                value: 2,
                message: "Name should be at least 2 characters",
              },
              validate: {
                notAdmin: (value)=> value !=='admin' || 'admin is not allowed.',
                isNotNumber: (value)=> isNaN(value) || 'Name can not be a number.',
                checkUsername: async (value)=>{
                  const exist = await checkIfUsernameExists(value);
                  return !exist || 'Username is already taken'
                }
              }
            })}
          />
        </label>
        {errors.name && <p>{errors.name.message}</p>}
        <br />
        <label>
          Email:
          <input type="email" {...register("email", { required: true })} />
        </label>
        {errors.email && <p>Email is required</p>}
        <br />
        <label>
          Password:
          <input type="password" {...register("password", {
              required: "Password is required",
              minLength: {
                value: 2,
                message: "Password should be at least 2 characters",
              },
            })} />
        </label>
        {errors.password && <p>{errors.password.message}</p>}
        <br />
        <label>
          Confirm Password:
          <input type="password" {...register("confirmPassword", {
              required: true,
              minLength: {
                value: 2,
                message: "Password should be at least 2 characters",
              },
              validate: (value)=> value === watch('password') || 'Passwords do not match.'
            })} />
        </label>
        {errors.confirmPassword && <p>{errors.confirmPassword.message}</p>}
        <button type="submit">Submit</button>
        <button type="button" onClick={()=>reset()}>Reset</button>
      </form>
    </div>
  );
}

export default FormLibrary;
