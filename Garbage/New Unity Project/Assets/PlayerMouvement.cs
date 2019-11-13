using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMouvement : MonoBehaviour
{
    public Rigidbody rb;
    
    // Costum variables
    public float forwardForce = 2000f;
    public float sideWaysForce = 500f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        // Add a forward force
        rb.AddForce(0, 0, forwardForce * Time.deltaTime);

        // Mouvement keys
        if (Input.GetKey("d"))  {
            rb.AddForce(sideWaysForce * Time.deltaTime, 0, 0);
        }

        if (Input.GetKey("a"))  {
            rb.AddForce(-sideWaysForce * Time.deltaTime, 0, 0);
        }

    }
}
