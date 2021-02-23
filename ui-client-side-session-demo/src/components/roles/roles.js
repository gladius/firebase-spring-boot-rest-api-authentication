import { useAuth } from "contexts/useAuth";

const Roles = () => {
  const { roles } = useAuth();

  return (
    <>
      {roles && (
        <div>
          <span className="label">User Roles</span>
          <span colSpan="3" height="50px">
            {roles &&
              roles.map((val, key) => (
                <span key={key} className="role">
                  {val}
                </span>
              ))}
          </span>
        </div>
      )}
    </>
  );
};

export default Roles;
